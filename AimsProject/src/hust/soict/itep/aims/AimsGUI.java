package hust.soict.itep.aims;

import javax.swing.SwingUtilities;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Book;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.media.Track;
import hust.soict.itep.aims.screen.StoreScreen;
import hust.soict.itep.aims.store.Store;

public class AimsGUI {
    public static void main(String[] args) {
        Store store = createStore();
        Cart cart = new Cart();
        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }

    private static Store createStore() {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Book book = new Book("Effective Java", "Programming", 45.50f);
        book.addAuthor("Joshua Bloch");

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "ABBA", 12.99f);
        cd.addTrack(new Track("Dancing Queen", 231));
        cd.addTrack(new Track("Mamma Mia", 213));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book);
        store.addMedia(cd);

        return store;
    }
}
