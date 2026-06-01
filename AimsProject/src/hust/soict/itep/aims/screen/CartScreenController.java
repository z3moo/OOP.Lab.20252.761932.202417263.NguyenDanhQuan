package hust.soict.itep.aims.screen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.exceptions.PlayerException;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.MediaComparatorByCostTitle;
import hust.soict.itep.aims.media.MediaComparatorByTitleCost;
import hust.soict.itep.aims.media.Playable;

public class CartScreenController {

    private final Cart cart;
    private final StoreScreen storeScreen;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;

    @FXML private Button btnPlay;
    @FXML private Button btnOrder;
    @FXML private Button btnRemove;

    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;

    @FXML private Label costText;

    public CartScreenController(Cart cart, StoreScreen storeScreen) {
        this.cart = cart;
        this.storeScreen = storeScreen;
    }

    @FXML
    private void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tblMedia.setItems(cart.getItemsOrdered());
        costText.setText("0$");
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> updateTotalCost());

        tblMedia.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> obs, Media oldVal, Media newVal) {
                        if (newVal != null) {
                            updateButtonBar(newVal);
                        }
                    }
                });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String oldVal, String newVal) {
                showFilteredMedia(newVal);
            }
        });

        updateTotalCost();
    }

    private void showFilteredMedia(String value) {
        if (value == null || value.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }
        if (radioBtnFilterId.isSelected()) {
            try {
                int id = Integer.parseInt(value.trim());
                tblMedia.setItems(cart.filterByID(id));
            } catch (NumberFormatException e) {
                tblMedia.setItems(FXCollections.observableArrayList());
            }
        } else {
            tblMedia.setItems(cart.filterByTitle(value));
        }
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }

    @FXML
    private void btnOrderPressed(ActionEvent e) {
        if (cart.getItemsOrdered().isEmpty()) {
            alert("Cart is empty");
            return;
        }
        alert("Order placed");
        cart.clearCart();
        // Sort/filter may have replaced the table's items with a snapshot list,
        // so re-bind to the live cart so the empty state shows.
        tblMedia.setItems(cart.getItemsOrdered());
        tfFilter.clear();
        updateTotalCost();
    }

    @FXML
    private void btnRemovePressed(ActionEvent e) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
        }
    }

    @FXML
    private void btnPlayPressed(ActionEvent e) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) {
            return;
        }
        if (!(media instanceof Playable)) {
            alert("This media cannot be played");
            return;
        }
        if (media instanceof hust.soict.itep.aims.media.Disc) {
            int len = ((hust.soict.itep.aims.media.Disc) media).getLength();
            if (len <= 0) {
                alert("Cannot play " + media.getTitle() + ": length is non-positive");
                return;
            }
        }
        alert(playMessage(media));
    }

    private String playMessage(Media media) {
        StringBuilder sb = new StringBuilder();
        if (media instanceof hust.soict.itep.aims.media.DigitalVideoDisc) {
            hust.soict.itep.aims.media.DigitalVideoDisc dvd =
                    (hust.soict.itep.aims.media.DigitalVideoDisc) media;
            sb.append("Playing DVD: ").append(dvd.getTitle()).append('\n');
            sb.append("DVD length: ").append(dvd.getLength());
        } else if (media instanceof hust.soict.itep.aims.media.CompactDisc) {
            hust.soict.itep.aims.media.CompactDisc cd =
                    (hust.soict.itep.aims.media.CompactDisc) media;
            sb.append("Playing CD: ").append(cd.getTitle()).append('\n');
            sb.append("CD artist: ").append(cd.getArtist()).append('\n');
            sb.append("CD length: ").append(cd.getLength());
        } else {
            sb.append("Playing: ").append(media.getTitle());
        }
        return sb.toString();
    }

    private void updateTotalCost() {
        costText.setText(String.format("%.2f$", cart.totalCost()));
    }

    private void alert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void viewStore() {
        if (storeScreen != null) {
            storeScreen.showStoreView();
        }
    }

    @FXML
    private void viewUpdateBook() {
        if (storeScreen != null) {
            storeScreen.showUpdateView("BOOK");
        }
    }

    @FXML
    private void viewUpdateCD() {
        if (storeScreen != null) {
            storeScreen.showUpdateView("CD");
        }
    }

    @FXML
    private void viewUpdateDVD() {
        if (storeScreen != null) {
            storeScreen.showUpdateView("DVD");
        }
    }

    @FXML
    private void sortByName() {
        ArrayList<Media> sorted = new ArrayList<>(cart.getItemsOrdered());
        Collections.sort(sorted, new MediaComparatorByTitleCost());
        tblMedia.setItems(FXCollections.observableArrayList(sorted));
    }

    @FXML
    private void sortByCost() {
        ArrayList<Media> sorted = new ArrayList<>(cart.getItemsOrdered());
        Collections.sort(sorted, new MediaComparatorByCostTitle());
        tblMedia.setItems(FXCollections.observableArrayList(sorted));
    }
}
