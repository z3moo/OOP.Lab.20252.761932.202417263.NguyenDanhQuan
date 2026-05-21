package hust.soict.itep.aims;

import java.util.Scanner;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Book;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;
import hust.soict.itep.aims.media.Track;
import hust.soict.itep.aims.store.Store;

public class Aims {
    public static void main(String[] args) {
        Store store = createStore();
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            showMenu();
            choice = readInt(scanner);

            switch (choice) {
                case 1:
                    viewStore(scanner, store, cart);
                    break;
                case 2:
                    updateStore(scanner, store);
                    break;
                case 3:
                    viewCart(scanner, cart);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
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

    private static void viewStore(Scanner scanner, Store store, Cart cart) {
        int choice;
        do {
            store.printStore();
            storeMenu();
            choice = readInt(scanner);

            switch (choice) {
                case 1:
                    seeMediaDetails(scanner, store, cart);
                    break;
                case 2:
                    addMediaFromStoreToCart(scanner, store, cart);
                    break;
                case 3:
                    playMediaFromStore(scanner, store);
                    break;
                case 4:
                    viewCart(scanner, cart);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void seeMediaDetails(Scanner scanner, Store store, Cart cart) {
        Media media = askMediaInStore(scanner, store);

        if (media == null) {
            return;
        }

        System.out.println(media);

        int choice;
        do {
            mediaDetailsMenu();
            choice = readInt(scanner);

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    printNumberOfDVDsIfDVD(media, cart);
                    break;
                case 2:
                    playMedia(media);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void addMediaFromStoreToCart(Scanner scanner, Store store, Cart cart) {
        Media media = askMediaInStore(scanner, store);

        if (media != null) {
            cart.addMedia(media);
            printNumberOfDVDsIfDVD(media, cart);
        }
    }

    private static void playMediaFromStore(Scanner scanner, Store store) {
        Media media = askMediaInStore(scanner, store);

        if (media != null) {
            playMedia(media);
        }
    }

    private static Media askMediaInStore(Scanner scanner, Store store) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("No media with this title was found in the store.");
        }

        return media;
    }

    private static void updateStore(Scanner scanner, Store store) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");

        int choice = readInt(scanner);

        switch (choice) {
            case 1:
                Media media = createMediaFromInput(scanner);
                if (media != null) {
                    store.addMedia(media);
                }
                break;
            case 2:
                System.out.print("Enter media title to remove: ");
                String title = scanner.nextLine();
                Media mediaToRemove = store.findMediaByTitle(title);
                if (mediaToRemove != null) {
                    store.removeMedia(mediaToRemove);
                } else {
                    System.out.println("No media with this title was found in the store.");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static Media createMediaFromInput(Scanner scanner) {
        System.out.println("Choose media type:");
        System.out.println("1. DVD");
        System.out.println("2. CD");
        System.out.println("3. Book");
        System.out.println("0. Back");

        int type = readInt(scanner);

        if (type == 0) {
            return null;
        }

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter cost: ");
        float cost = readFloat(scanner);

        switch (type) {
            case 1:
                System.out.print("Enter director: ");
                String dvdDirector = scanner.nextLine();
                System.out.print("Enter length: ");
                int dvdLength = readInt(scanner);
                return new DigitalVideoDisc(title, category, dvdDirector, dvdLength, cost);
            case 2:
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                CompactDisc cd = new CompactDisc(title, category, artist, cost);
                addTracksToCD(scanner, cd);
                return cd;
            case 3:
                Book book = new Book(title, category, cost);
                addAuthorsToBook(scanner, book);
                return book;
            default:
                System.out.println("Invalid media type.");
                return null;
        }
    }

    private static void addTracksToCD(Scanner scanner, CompactDisc cd) {
        System.out.print("How many tracks do you want to add? ");
        int numberOfTracks = readInt(scanner);

        for (int i = 0; i < numberOfTracks; i++) {
            System.out.print("Enter track title: ");
            String trackTitle = scanner.nextLine();
            System.out.print("Enter track length: ");
            int trackLength = readInt(scanner);
            cd.addTrack(new Track(trackTitle, trackLength));
        }
    }

    private static void addAuthorsToBook(Scanner scanner, Book book) {
        System.out.print("How many authors do you want to add? ");
        int numberOfAuthors = readInt(scanner);

        for (int i = 0; i < numberOfAuthors; i++) {
            System.out.print("Enter author name: ");
            book.addAuthor(scanner.nextLine());
        }
    }

    private static void viewCart(Scanner scanner, Cart cart) {
        int choice;
        do {
            cart.printCart();
            cartMenu();
            choice = readInt(scanner);

            switch (choice) {
                case 1:
                    filterCart(scanner, cart);
                    break;
                case 2:
                    sortCart(scanner, cart);
                    break;
                case 3:
                    System.out.print("Enter media title to remove: ");
                    cart.removeMediaByTitle(scanner.nextLine());
                    break;
                case 4:
                    playMediaFromCart(scanner, cart);
                    break;
                case 5:
                    cart.emptyCart();
                    System.out.println("An order has been created. The cart is now empty.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void filterCart(Scanner scanner, Cart cart) {
        System.out.println("Filter options:");
        System.out.println("1. By id");
        System.out.println("2. By title");
        int choice = readInt(scanner);

        switch (choice) {
            case 1:
                System.out.print("Enter id: ");
                cart.searchByID(readInt(scanner));
                break;
            case 2:
                System.out.print("Enter title: ");
                cart.searchByTitle(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void sortCart(Scanner scanner, Cart cart) {
        System.out.println("Sort options:");
        System.out.println("1. By title");
        System.out.println("2. By cost");
        int choice = readInt(scanner);

        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                cart.printCart();
                break;
            case 2:
                cart.sortByCostTitle();
                cart.printCart();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void playMediaFromCart(Scanner scanner, Cart cart) {
        System.out.print("Enter media title: ");
        Media media = cart.findMediaByTitle(scanner.nextLine());

        if (media == null) {
            System.out.println("No media with this title was found in the cart.");
        } else {
            playMedia(media);
        }
    }

    private static void playMedia(Media media) {
        if (media instanceof Playable) {
            Playable playable = (Playable) media;
            playable.play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void printNumberOfDVDsIfDVD(Media media, Cart cart) {
        if (media instanceof DigitalVideoDisc) {
            System.out.println("Number of DVDs in current cart: " + cart.getNumberOfDVDs());
        }
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            scanner.nextLine();
        }

        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    private static float readFloat(Scanner scanner) {
        while (!scanner.hasNextFloat()) {
            System.out.print("Please enter a valid number: ");
            scanner.nextLine();
        }

        float number = scanner.nextFloat();
        scanner.nextLine();
        return number;
    }
}
