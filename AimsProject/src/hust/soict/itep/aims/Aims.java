package hust.soict.itep.aims;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.disc.DigitalVideoDisc;

public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Inception", "Science Fiction", "Christopher Nolan", 148, 22.99f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Toy Story", "Animation", "John Lasseter", 81, 17.99f);
        //DigitalVideoDisc[] dvdList = {dvd4, dvd5}; (array)
        anOrder.addDigitalVideoDisc(dvd4, dvd5); //arbitrary number

        anOrder.removeDigitalVideoDisc(dvd1);

        anOrder.searchByID(1);
        anOrder.searchByTitle("Inception");
        anOrder.searchByTitle("Avatar");

        anOrder.printCart();
    }
}
