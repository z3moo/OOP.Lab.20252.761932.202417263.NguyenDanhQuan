import AIMS.DigitalVideoDisc;

public class Store {
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[100];

    public void addDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < itemsInStore.length; i++) {
            if (itemsInStore[i] == null) {
                itemsInStore[i] = disc;
                System.out.println("The disc has been added to the store");
                return;
            }
        }
        System.out.println("The store is full");
    }

    public void removeDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < itemsInStore.length; i++) {
            if (itemsInStore[i] == disc) {
                for (int j = i; j < itemsInStore.length - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[itemsInStore.length - 1] = null;
                System.out.println("The disc has been removed from the store");
                return;
            }
        }
        System.out.println("The disc was not found in the store");
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in the store:");
        for (DigitalVideoDisc disc : itemsInStore) {
            if (disc != null) {
                System.out.println(disc);
            }
        }
    }
}
