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
        stage.show();
    }

    public static Stage getStage() {
        return AdolfFront.stage;
    }

}