package hust.soict.itep.test.cart;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Book;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.DigitalVideoDisc;

public class CartSortTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "ABBA", 24.95f);
        Book book = new Book("Effective Java", "Programming", 45.50f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);

        cart.addMedia(dvd);
        cart.addMedia(cd);
        cart.addMedia(book);
        cart.addMedia(dvd2);

        System.out.println("Sort by title then cost:");
        cart.sortByTitleCost();
        cart.printCart();

        System.out.println("Sort by cost then title:");
        cart.sortByCostTitle();
        cart.printCart();
    }
}
