package edu.erciyes.trafficsimulation;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;

public class CarCreator {

    public ArrayList<Car> list = new ArrayList<>();

    public void creator(int number){
        for (int i = 0; i < number; i++){
            list.add(new Car());
        }
    }

    public void animator(Line line, Pane pane){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            if (!list.isEmpty()) {
                Car car = list.remove(0);
                pane.getChildren().add(car.getShape());
                PathTransition path = new PathTransition();
                path.setNode(car.getShape());
                path.setPath(line);
                path.setDuration(Duration.seconds(3));
                path.setOnFinished(e -> pane.getChildren().remove(car.getShape()));
                path.play();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
