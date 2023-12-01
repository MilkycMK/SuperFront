package net.mlk.adolffront;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.LoginRegisterScreen;
import net.mlk.adolffront.utils.IResizable;

public class AdolfFront extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        AdolfFront.stage = stage;
        stage.setTitle("AdolfFront");
        setupStageListeners();
        setScreen(new LoginRegisterScreen());
        stage.show();
    }

    private static void setupStageListeners() {
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
        stage.getScene().addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        screen.setBackground(Environment.BACKGROUND);
        return stage;
    }

    public static void redrawCurrentScene() {
        Scene currentScene = stage.getScene();
        if (currentScene != null && currentScene.getRoot() instanceof IResizable) {
            ((IResizable) currentScene.getRoot()).redraw();
        }
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}