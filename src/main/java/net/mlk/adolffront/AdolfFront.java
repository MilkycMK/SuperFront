package net.mlk.adolffront;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.LoginRegisterScreen;

public class AdolfFront extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        Scene scene;

        LoginRegisterScreen main = new LoginRegisterScreen();
        scene = new Scene(main, Environment.WIDTH, Environment.HEIGHT);
        scene.setFill(Environment.BACKGROUND_COLOR);

        AdolfFront.stage = stage;
        stage.setTitle("AdolfFront");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}