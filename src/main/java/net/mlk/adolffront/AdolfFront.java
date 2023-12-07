package net.mlk.adolffront;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.LoginRegisterScreen;
import net.mlk.adolffront.screens.menu.MenuScreen;
import net.mlk.adolffront.screens.todo.TodoScreen;
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
        stage.getIcons().add(new Image(AdolfFront.class.getResourceAsStream("/assets/logo.png")));
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
        MenuScreen screen = new MenuScreen();
        screen.addElements(new TodoScreen(), new TodoScreen(), new TodoScreen());
        setScreen(screen);
    }

    public static void loadUserProfile(Json json) {
        Environment.name = json.getString("name");
        Environment.token = json.getString("token");
    }

    public static void deleteUserProfile() {
        File userProfileFile = new File(Environment.filePath);
        if (userProfileFile.exists()) {
            userProfileFile.delete();
        }
        setScreen(new LoginRegisterScreen());
    }

    public static Stage setScreen(Pane screen) {
        return setScreen(screen, Environment.width, Environment.height);
    }

    public static Stage setScreen(Pane screen, double width, double height) {
        if (stage.getScene() != null && stage.getScene().getRoot() != null) {
            ((Pane) stage.getScene().getRoot()).getChildren().clear();
        }
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