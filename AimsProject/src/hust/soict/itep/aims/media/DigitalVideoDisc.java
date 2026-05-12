package hust.soict.itep.aims.media;

public class DigitalVideoDisc extends Media {
    private static int nbDigitalVideoDiscs = 0;
    private String director;
    private int length;

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + director + " - " + length + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return getTitle().equals(title);
    }

    public DigitalVideoDisc(String title) {
        setId(++nbDigitalVideoDiscs);
        setTitle(title);
        setCategory("");
        this.director = "";
        this.length = 0;
        setCost(0.0f);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        setId(++nbDigitalVideoDiscs);
        setTitle(title);
        setCategory(category);
        this.director = "";
        this.length = 0;
        setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        setId(++nbDigitalVideoDiscs);
        setTitle(title);
        setCategory(category);
        this.director = director;
        this.length = 0;
        setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        setId(++nbDigitalVideoDiscs);
        setTitle(title);
        setCategory(category);
        this.director = director;
        this.length = length;
        setCost(cost);
    }
}
