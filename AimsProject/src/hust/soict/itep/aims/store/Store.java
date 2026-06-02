package hust.soict.itep.aims.store;

import java.util.ArrayList;

import hust.soict.itep.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (itemsInStore.contains(media)) {
            System.out.println("The media is already in the store");
        } else {
            itemsInStore.add(media);
            System.out.println("The media has been added to the store");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media has been removed from the store");
        } else {
            System.out.println("The media was not found in the store");
        }
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in the store:");
        for (Media media : itemsInStore) {
            System.out.println(media);
        }
        System.out.println("***************************************************");
    }

    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}
