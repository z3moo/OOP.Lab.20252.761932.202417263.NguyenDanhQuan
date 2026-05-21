package hust.soict.itep.test.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.itep.aims.media.Book;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Track;

public class PolymorphismTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<Media>();

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "ABBA", 12.99f);
        cd.addTrack(new Track("Dancing Queen", 231));
        cd.addTrack(new Track("Mamma Mia", 213));

        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);

        Book book = new Book("Effective Java", "Programming", 45.50f);
        book.addAuthor("Joshua Bloch");

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}
