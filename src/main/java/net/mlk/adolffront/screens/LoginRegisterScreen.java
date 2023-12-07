package net.mlk.adolffront.screens;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.controllers.LoginRegisterController;
import net.mlk.adolffront.utils.IResizable;
import net.mlk.adolffront.utils.StyleUtils;

public class LoginRegisterScreen extends BorderPane implements IResizable {
    public enum ScreenType { LOGIN, REGISTER }
    private final LoginRegisterController controller;
    private ScreenType currentScreen;
    private VBox form;
    private Text header;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField passwordRepeatField;
    private Button submitButton;
    private Text changeScreen;
    private Text errorText;

    public LoginRegisterScreen() {
        this.controller = new LoginRegisterController(this);
        this.initFields();
        this.drawScreen(ScreenType.LOGIN);
    }

    public void drawScreen(ScreenType screenType) {
        double width = Environment.width / 2;
        double fieldWidth = width / 2;
        double height = Environment.height / 1.4;
        double fieldHeight = height / 10;
        Font font = StyleUtils.createFont(fieldWidth / 15);
        Font headerFont = StyleUtils.createFont(fieldWidth / 8);

        this.form.setSpacing(height / 30);
        this.form.setMaxWidth(width);
        this.form.setMaxHeight(height);

        this.errorText.setWrappingWidth(fieldWidth);

        if (screenType == ScreenType.REGISTER) {
            this.header.setText("Регистрация");
            this.submitButton.setText("Зарегистрироваться");
            this.changeScreen.setText("Войти");
            this.form.getChildren().add(3, this.passwordRepeatField);
        } else {
            this.header.setText("Авторизация");
            this.submitButton.setText("Войти");
            this.changeScreen.setText("Регистрация");
            this.form.getChildren().remove(this.passwordRepeatField);
        }

        for (Node node : this.form.getChildren()) {
            if (node instanceof Control) {
                Control control = (Control) node;
                control.setMaxWidth(fieldWidth);
                control.setMinHeight(fieldHeight);
                if (control instanceof TextInputControl) {
                    ((TextInputControl) control).setFont(font);
                } else if (control instanceof Button) {
                    ((Button) control).setFont(font);
                }
            } else if (node instanceof Text) {
                Text text = (Text) node;
                if (text == this.header) {
                    text.setFont(headerFont);
                    continue;
                }
                text.setFont(font);
            }
        }

        this.currentScreen = screenType;
    }

    private void initFields() {
        Font font = StyleUtils.createFont();

        this.form = new VBox();
        ObservableList<Node> child = this.form.getChildren();
        this.form.setBackground(Environment.PANELS_BACKGROUND);
        this.form.setAlignment(Pos.CENTER);

        this.header = StyleUtils.createText(null);
        this.errorText = StyleUtils.createText(null, font, Color.RED);
        this.errorText.setTextAlignment(TextAlignment.CENTER);

        this.usernameField = StyleUtils.createTextField(null, "Логин", font, 0, 0);
        this.passwordField = StyleUtils.createPasswordField(null, "Пароль", font, 0, 0);
        this.passwordRepeatField = StyleUtils.createPasswordField(null, "Повтор пароля", font, 0, 0);
        this.submitButton = StyleUtils.createButton(null, font, 0, 0);
        submitButton.setOnAction((e) -> this.controller.onSubmitClick());

        this.changeScreen = StyleUtils.createText(null, font, Environment.BUTTONS_COLOR);
        this.changeScreen.setCursor(Cursor.HAND);
        this.changeScreen.setOnMouseClicked((e) -> {
            this.drawScreen(this.currentScreen == ScreenType.LOGIN ? ScreenType.REGISTER : ScreenType.LOGIN);
            this.errorText.setText("");
        });
        this.form.getChildren().addAll(this.header, this.usernameField, this.passwordField, this.submitButton,
                this.changeScreen, this.errorText);
        super.setCenter(this.form);
    }

    @Override
    public void redraw() {
        this.drawScreen(this.currentScreen);
    }

    public void setErrorText(String text) {
        this.errorText.setText(text);
    }

    public String getNameText() {
        if (this.usernameField == null) {
            return null;
        }
        return this.usernameField.getText();
    }

    public String getPasswordText() {
        if (this.passwordField == null) {
            return null;
        }
        return this.passwordField.getText();
    }

    public String getRepeatPasswordText() {
        if (this.passwordRepeatField == null) {
            return null;
        }
        return this.passwordRepeatField.getText();
    }

    public ScreenType getCurrentScreen() {
        return this.currentScreen;
    }

}