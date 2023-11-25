package net.mlk.adolffront.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;

public class LoginRegisterScreen extends BorderPane {
    private static final int HEIGHT = Environment.HEIGHT - 200;
    private static final int WIDTH = Environment.WIDTH - 400;
    private static final int FIELDS_HEIGHT = HEIGHT / 8;
    private static final int FIELDS_WIDTH = WIDTH / 2;

    public LoginRegisterScreen() {
        super.setBackground(Environment.TRANSPARENT_BACKGROUND);
        this.drawForm();
    }

    private void drawForm() {
        VBox form = new VBox();
        form.setBackground(Environment.PANELS_BACKGROUND);
        form.setAlignment(Pos.CENTER);
        form.setSpacing(20);
        form.setMaxHeight(HEIGHT);
        form.setMaxWidth(WIDTH);

        Text text = new Text("Авторизация");
        text.setFont(Environment.getFont(30));
        text.setFill(Color.WHITE);
        BorderPane.setMargin(text, new Insets(20, 0, 0, 0));
        BorderPane.setAlignment(text, Pos.CENTER);
        form.getChildren().add(text);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Логин");
        usernameField.setFont(Environment.getFont(15));
        usernameField.setMaxWidth(FIELDS_WIDTH);
        usernameField.setMinHeight(FIELDS_HEIGHT);
        usernameField.setBackground(Environment.BACKGROUND);

        TextField passwordField = new TextField();
        passwordField.setPromptText("Пароль");
        passwordField.setFont(Environment.getFont(15));
        passwordField.setMaxWidth(FIELDS_WIDTH);
        passwordField.setMinHeight(FIELDS_HEIGHT);
        form.getChildren().addAll(usernameField, passwordField);
        passwordField.setBackground(Environment.BACKGROUND);

        Button loginButton = new Button("Войти");
        loginButton.setMaxWidth(FIELDS_WIDTH);
        loginButton.setMinHeight(FIELDS_HEIGHT);
        form.getChildren().add(loginButton);
        loginButton.setBackground(Environment.BUTTON_BACKGROUND);

        super.setCenter(form);
    }
}
