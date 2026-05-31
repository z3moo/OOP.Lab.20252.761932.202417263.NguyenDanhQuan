package hust.soict.itep.aims.cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import hust.soict.itep.aims.exceptions.PlayerException;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(media);
            System.out.println("Added " + media.getTitle());
        } else {
            System.out.println("The cart is full");
        }
    }

    public void addMedia(Media... mediaList) {
        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Removed " + media.getTitle());
        } else {
            System.out.println("The media was not found in the cart");
        }
    }

    public void clearCart() {
        itemsOrdered.clear();
        System.out.println("The cart has been cleared");
    }

    public void emptyCart() {
        clearCart();
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i));
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public void searchByID(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println(media);
                return;
            }
        }
        System.out.println("No matching media found.");
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println(media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching media found.");
        }
    }

    public ObservableList<Media> filterByID(int id) {
        ObservableList<Media> filtered = FXCollections.observableArrayList();
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                filtered.add(media);
            }
        }
        return filtered;
    }

    public ObservableList<Media> filterByTitle(String title) {
        ObservableList<Media> filtered = FXCollections.observableArrayList();
        if (title == null) return filtered;
        String needle = title.toLowerCase();
        for (Media media : itemsOrdered) {
            if (media.getTitle() != null && media.getTitle().toLowerCase().contains(needle)) {
                filtered.add(media);
            }
        }
        return filtered;
    }

    public void sortByTitleCost() {
        itemsOrdered.sort(Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        itemsOrdered.sort(Media.COMPARE_BY_COST_TITLE);
    }

    public Media findMediaByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void removeMediaByTitle(String title) {
        Media media = findMediaByTitle(title);
        if (media != null) {
            removeMedia(media);
        } else {
            System.out.println("The media was not found in the cart");
        }
    }

    public int getNumberOfDVDs() {
        int count = 0;
        for (Media media : itemsOrdered) {
            if (media instanceof DigitalVideoDisc) {
                count++;
            }
        }
        return count;
    }

    public void playMedia(Media media) throws PlayerException {
        if (itemsOrdered.contains(media)) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            }
        }
    }
}
