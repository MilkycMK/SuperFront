package net.mlk.adolffront;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdolfFront extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        AdolfFront.stage = stage;
        stage.getIcons().add(new Image(AdolfFront.class.getResourceAsStream("/assets/logo.png")));
        stage.setTitle("AdolfFront");
        stage.show();
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}