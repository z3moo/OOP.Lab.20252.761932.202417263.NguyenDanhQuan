package hust.soict.itep.aims.screen;

import java.io.IOException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import hust.soict.itep.aims.cart.Cart;

public class CartScreen {
    private final Cart cart;
    private JFXPanel fxPanel;

    public CartScreen(Cart cart) {
        this.cart = cart;
    }

    public JFXPanel getCartPanel(StoreScreen storeScreen) {
        fxPanel = new JFXPanel();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                CartScreenController controller = new CartScreenController(cart, storeScreen);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return fxPanel;
    }
}
