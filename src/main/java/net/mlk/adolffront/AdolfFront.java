package net.mlk.adolffront;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.login.LoginRegisterScreen;

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
        Scene scene = new Scene(new LoginRegisterScreen(), Environment.width, Environment.height);
        scene.setFill(Environment.BACKGROUND_COLOR);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}