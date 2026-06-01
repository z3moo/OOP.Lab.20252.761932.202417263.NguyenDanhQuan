package hust.soict.itep.aims.screen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.application.Platform;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.media.Disc;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this(media, null);
    }

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(e -> {
            if (cart != null) {
                // Cart's list is a JavaFX ObservableList bound to a TableView,
                // so the mutation must run on the FX Application Thread.
                Platform.runLater(() -> cart.addMedia(media));
                JOptionPane.showMessageDialog(this,
                        "Added " + media.getTitle() + " to cart");
            }
        });
        container.add(addToCart);

        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(e -> showPlayDialog());
            container.add(play);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void showPlayDialog() {
        if (media instanceof Disc && ((Disc) media).getLength() <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Cannot play " + media.getTitle() + ": length is non-positive",
                    "Play", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (media instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            sb.append("Playing DVD: ").append(dvd.getTitle()).append('\n');
            sb.append("DVD length: ").append(dvd.getLength());
        } else if (media instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) media;
            sb.append("Playing CD: ").append(cd.getTitle()).append('\n');
            sb.append("CD artist: ").append(cd.getArtist()).append('\n');
            sb.append("CD length: ").append(cd.getLength());
        } else {
            sb.append("Playing: ").append(media.getTitle());
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Play",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
