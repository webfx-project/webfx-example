package org.example.webfxexample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WebFxExampleApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Hello world!");
        button.setEffect(new DropShadow(20, 10, 10, Color.BLACK));
        StackPane root = new StackPane(button);
        button.setOnAction(event -> {
            boolean forward = root.getRotate() == 0;
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(2), new KeyValue(root.rotateProperty(), forward ? 360 : 0 )),
                    new KeyFrame(Duration.seconds(2), new KeyValue(button.scaleXProperty(), forward ? 5 : 1 )),
                    new KeyFrame(Duration.seconds(2), new KeyValue(button.scaleYProperty(), forward ? 5 : 1 ))
            );
            timeline.play();
        });
        root.setBackground(new Background(new BackgroundFill(LinearGradient.valueOf("to right, #B2F4B6, #3BF0E4, #C2A0FD, #EA5DAD, #FF7571, #FFE580"), null, null)));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

}