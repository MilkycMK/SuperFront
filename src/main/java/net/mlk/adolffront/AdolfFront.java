package net.mlk.adolffront;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.LoginRegisterScreen;
import net.mlk.adolffront.screens.TodoScreen;
import net.mlk.adolffront.utils.IResizable;
import net.mlk.jmson.Json;

import java.io.File;

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
        File tokenFile = new File(Environment.filePath);
        if (!tokenFile.exists()) {
            setScreen(new LoginRegisterScreen());
        } else {
            loadUserProfile(tokenFile);
        }
        stage.show();
    }

    public static void loadUserProfile(File file) {
        loadUserProfile(new Json(file));
        setScreen(new TodoScreen());
    }

    public static void loadUserProfile(Json json) {
        Environment.name = json.getString("name");
        Environment.token = json.getString("token");
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