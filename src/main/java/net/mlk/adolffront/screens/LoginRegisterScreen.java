package net.mlk.adolffront.screens;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.StyleUtils;

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

        Text text = StyleUtils.createText("Авторизация", 30);

        Font fieldFont = StyleUtils.createFont(15);
        TextField usernameField = createTextField("Логин", fieldFont);
        PasswordField passwordField = createPasswordField("Пароль", fieldFont);
        Button loginButton = createButton("Войти", fieldFont);

        form.getChildren().addAll(text, usernameField, passwordField, loginButton);
        super.setCenter(form);
    }

    private TextField createTextField(String promptText, Font font) {
        TextField field = new TextField();
        field.setPromptText(promptText);
        field.setFont(font);
        field.setMaxWidth(FIELDS_WIDTH);
        field.setMinHeight(FIELDS_HEIGHT);
        StyleUtils.setInnerTextColor(field, "white");
        field.setBackground(Environment.BACKGROUND);
        return field;
    }

    private PasswordField createPasswordField(String promptText, Font font) {
        PasswordField field = new PasswordField();
        field.setPromptText(promptText);
        field.setFont(font);
        field.setMaxWidth(FIELDS_WIDTH);
        field.setMinHeight(FIELDS_HEIGHT);
        StyleUtils.setInnerTextColor(field, "white");
        field.setBackground(Environment.BACKGROUND);
        return field;
    }

    private Button createButton(String text, Font font) {
        Button button = new Button(text);
        button.setMaxWidth(FIELDS_WIDTH);
        button.setMinHeight(FIELDS_HEIGHT);
        button.setFont(font);
        StyleUtils.setTextColor(button, "white");
        button.setCursor(Cursor.HAND);
        button.setBackground(Environment.BUTTON_BACKGROUND);
        return button;
    }

}
