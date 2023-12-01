package net.mlk.adolffront;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.LoginRegisterScreen;
import net.mlk.adolffront.utils.notifications.Notification;
import net.mlk.adolffront.utils.Resizable;
import net.mlk.adolffront.utils.notifications.NotificationPanel;
import net.mlk.adolffront.utils.notifications.NotificationScreen;

import java.util.ArrayList;

public class AdolfFront extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        AdolfFront.stage = stage;
        stage.setTitle("AdolfFront");
        setupResizableListeners();
        setScreen(new LoginRegisterScreen());
        stage.show();
    }

    private static void setupResizableListeners() {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            Environment.width = newVal.doubleValue();
            redrawCurrentScene();
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            Environment.height = newVal.doubleValue();
            redrawCurrentScene();
        });
    }

    public static Stage setScreen(Pane screen) {
        return setScreen(screen, Environment.width, Environment.height);
    }

    public static Stage setScreen(Pane screen, double width, double height) {
        stage.setScene(new Scene(screen, width, height));
        screen.setBackground(Environment.BACKGROUND);
        return stage;
    }

    public static void redrawCurrentScene() {
        Scene currentScene = stage.getScene();
        if (currentScene != null && currentScene.getRoot() instanceof Resizable) {
            ((Resizable) currentScene.getRoot()).redraw();
        }
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}