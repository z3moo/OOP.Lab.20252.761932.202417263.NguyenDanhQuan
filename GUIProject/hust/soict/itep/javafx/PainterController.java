package hust.soict.itep.javafx;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;
    private boolean pen;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if(pen)
        {
            Circle circle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            drawingAreaPane.getChildren().add(circle);
        }
        else
        {
            // Eraser: remove circles within eraser radius
            double eraserRadius = 10.0;
            drawingAreaPane.getChildren().removeIf(node -> {
                if (node instanceof Circle) {
                    Circle circle = (Circle) node;
                    double dx = circle.getCenterX() - event.getX();
                    double dy = circle.getCenterY() - event.getY();
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    return distance <= eraserRadius;
                }
                return false;
            });
        }
    }

    @FXML
    void penSelected(ActionEvent event)
    {
        pen = true;
    }

    @FXML
    void eraserSelected(ActionEvent event)
    {
        pen = false;
    }


    
}