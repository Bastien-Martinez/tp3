package fr.univ_amu.iut.exercice7;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import static javafx.beans.binding.Bindings.negate;
import static javafx.beans.binding.Bindings.when;

public class Ball {

    private final DoubleProperty positionX = new SimpleDoubleProperty(50);
    private final DoubleProperty positionY = new SimpleDoubleProperty(50);
    private final DoubleProperty velocityX = new SimpleDoubleProperty(0); //en pixel par nanosecond
    private final DoubleProperty velocityY = new SimpleDoubleProperty(0); //en pixel par nanosecond
    private final DoubleProperty radius = new SimpleDoubleProperty(20);
    private final Pane parent;
    private Circle ball = new Circle();

    private BooleanExpression isBouncingOffVerticalWall;
    private BooleanExpression isBouncingOffHorizontalWall;

    private NumberBinding bounceOffVerticalWall;
    private NumberBinding bounceOffHorizontalWall;

    public Ball(Pane parent) {
        this.parent = parent;
        velocityX.setValue(550E-9);
        velocityY.setValue(500E-9);

        parent.getChildren().add(ball);
        createBindings();
    }

    private void createBindings() {
        ball.radiusProperty().bind(radius);
        ball.centerXProperty().bind(positionX);
        ball.centerYProperty().bind(positionY);
        isBouncingOffHorizontalWall = Bindings.lessThan(positionY, radius).or(positionY.greaterThan(parent.heightProperty().subtract(radius)));
        isBouncingOffVerticalWall = Bindings.lessThan(positionX, radius).or(positionX.greaterThan(parent.widthProperty().subtract(radius)));
        bounceOffHorizontalWall = Bindings.when(isBouncingOffHorizontalWall).then(velocityY.negate()).otherwise(velocityY);
        bounceOffVerticalWall = Bindings.when(isBouncingOffVerticalWall).then(velocityX.negate()).otherwise(velocityX);
    }

    public void move(long elapsedTimeInNanoseconds) {
        velocityY.set(bounceOffHorizontalWall.doubleValue());
        velocityX.set(bounceOffVerticalWall.doubleValue());

        positionY.set(positionY.doubleValue()+elapsedTimeInNanoseconds*velocityY.get());
        positionX.set(positionX.doubleValue()+elapsedTimeInNanoseconds*velocityX.get());
    }
}
