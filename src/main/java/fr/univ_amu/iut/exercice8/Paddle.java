package fr.univ_amu.iut.exercice8;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;

import javax.xml.bind.Marshaller;

public class Paddle extends Rectangle {
    private DoubleProperty paddleY = new SimpleDoubleProperty();

    private double initPaddleTranslateY;
    private double paddleDragAnchorY;

    public Paddle(double x) {
        this.setHeight(50);
        this.setWidth(20);
        this.setStyle("-fx-background-color: red");
        this.setCursor(Cursor.CLOSED_HAND);
        this.setOnMousePressed(event -> {
            initPaddleTranslateY ;
        });

    }
}
