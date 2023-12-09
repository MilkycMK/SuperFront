package net.mlk.adolffront;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.mlk.adolffront.screens.login.LoginRegisterScreen;
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

        File tokenFile = new File(Environment.filePath);
        if (!tokenFile.exists()) {
            setScreen(new LoginRegisterScreen());
        } else {
            loadUserProfile(tokenFile);
        }
        stage.show();
    }

    public static void setScreen(Pane screen) {
        stage.setScene(new Scene(screen, Environment.width, Environment.height));
        stage.getScene().addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        screen.setBackground(Environment.BACKGROUND);
    }

    public static void loadUserProfile(File file) {
        Json json = new Json(file);
        Environment.name = json.getString("name");
        Environment.token = json.getString("token");

        setScreen(new BorderPane());
    }

    public static void deleteUserProfile() {
        File userProfileFile = new File(Environment.filePath);
        if (userProfileFile.exists()) {
            userProfileFile.delete();
        }
        setScreen(new LoginRegisterScreen());
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}