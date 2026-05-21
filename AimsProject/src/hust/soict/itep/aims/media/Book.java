package hust.soict.itep.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - " + authors + ": " + getCost() + " $";
    }

    public Book() {

    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }
}
