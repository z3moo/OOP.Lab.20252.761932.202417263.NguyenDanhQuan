package hust.soict.itep.test.disc;

import hust.soict.itep.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        swap(jungleDVD, cinderellaDVD);
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("Cinderella DVD title: " + cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
    }

    // swap cũ
    // public static void swap(Object o1, Object o2) {
    //     Object temp = o1;
    //     o1 = o2;
    //     o2 = temp;
    // }

    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        String title = dvd1.getTitle();
        String category = dvd1.getCategory();
        String director = dvd1.getDirector();
        int length = dvd1.getLength();
        float cost = dvd1.getCost();

        dvd1.setTitle(dvd2.getTitle());
        dvd1.setCategory(dvd2.getCategory());
        dvd1.setDirector(dvd2.getDirector());
        dvd1.setLength(dvd2.getLength());
        dvd1.setCost(dvd2.getCost());

        dvd2.setTitle(title);
        dvd2.setCategory(category);
        dvd2.setDirector(director);
        dvd2.setLength(length);
        dvd2.setCost(cost);
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}
