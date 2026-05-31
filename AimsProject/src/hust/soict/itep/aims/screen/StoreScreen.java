package hust.soict.itep.aims.screen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.store.Store;

public class StoreScreen extends JFrame {
    private static final String CARD_STORE = "store";
    private static final String CARD_CART = "cart";
    private static final String CARD_UPDATE = "update";

    private final Store store;
    private final Cart cart;

    private JPanel cards;
    private CardLayout cardLayout;

    private CartScreen cartScreen;
    private UpdateScreen updateScreen;

    public StoreScreen(Store store) {
        this(store, new Cart());
    }

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCards(), BorderLayout.CENTER);

        setTitle("AIMS - Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createCards() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(createStorePanel(), CARD_STORE);

        cartScreen = new CartScreen(cart);
        cards.add(cartScreen.getCartPanel(this), CARD_CART);

        updateScreen = new UpdateScreen(store);
        cards.add(updateScreen.getUpdatePanel(this), CARD_UPDATE);

        cardLayout.show(cards, CARD_STORE);
        return cards;
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartBtn = new JButton("View cart");
        cartBtn.setPreferredSize(new Dimension(120, 50));
        cartBtn.setMaximumSize(new Dimension(120, 50));
        cartBtn.addActionListener(e -> showCartView());

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartBtn);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addBook.addActionListener(e -> showUpdateView("BOOK"));
        addCD.addActionListener(e -> showUpdateView("CD"));
        addDVD.addActionListener(e -> showUpdateView("DVD"));
        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> showStoreView());

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> showCartView());

        menu.add(smUpdateStore);
        menu.add(viewStore);
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createStorePanel() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < 9 && i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }
        return center;
    }

    public void showStoreView() {
        SwingUtilities.invokeLater(() -> {
            cards.remove(0);
            cards.add(createStorePanel(), CARD_STORE, 0);
            cardLayout.show(cards, CARD_STORE);
        });
    }

    public void showCartView() {
        SwingUtilities.invokeLater(() -> cardLayout.show(cards, CARD_CART));
    }

    public void showUpdateView(String mediaType) {
        SwingUtilities.invokeLater(() -> {
            cardLayout.show(cards, CARD_UPDATE);
            if (updateScreen != null) {
                updateScreen.setMediaType(mediaType);
            }
        });
    }
}
