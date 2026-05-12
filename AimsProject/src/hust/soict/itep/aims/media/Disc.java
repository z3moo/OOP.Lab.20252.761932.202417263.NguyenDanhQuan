package hust.soict.itep.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Disc() {

    }

    public Disc(String title) {
        super(title);
        this.director = "";
        this.length = 0;
    }

    public Disc(String title, String category, float cost) {
        super(title, category, cost);
        this.director = "";
        this.length = 0;
    }

    public Disc(String title, String category, String director, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = 0;
    }

    public Disc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }
}
