package net.mlk.adolffront.screens;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Text errorText = new Text();
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField passwordRepeatField;

    public LoginRegisterScreen() {
        this.drawScreen(ScreenType.LOGIN);
        this.controller = new LoginRegisterController(this);
    }

    public void drawScreen(ScreenType screenType) {
        double width = Environment.width / 2;
        double fieldWidth = width / 2;
        double height = Environment.height / 1.4;
        double fieldHeight = height / 10;
        Font font = StyleUtils.createFont(fieldWidth / 15);
        Font headerFont = StyleUtils.createFont(fieldWidth / 8);

        this.errorText = StyleUtils.createText(errorText.getText(), font, Color.RED);
        this.errorText.setTextAlignment(TextAlignment.CENTER);
        this.errorText.setWrappingWidth(fieldWidth);

        VBox form = new VBox();
        ObservableList<Node> child = form.getChildren();
        form.setBackground(Environment.PANELS_BACKGROUND);
        form.setAlignment(Pos.CENTER);
        form.setSpacing(height / 30);
        form.setMaxWidth(width);
        form.setMaxHeight(height);

        Text text = StyleUtils.createText(screenType == ScreenType.LOGIN ? "Авторизация" : "Регистрация", headerFont);
        this.usernameField = StyleUtils.createTextField(this.getNameText(), "Логин", font, fieldWidth, fieldHeight);
        this.passwordField = StyleUtils.createPasswordField(this.getPasswordText(), "Пароль", font, fieldWidth,
                fieldHeight);
        Button submitButton = StyleUtils.createButton(screenType == ScreenType.LOGIN ? "Войти" : "Зарегистрироваться",
                font, fieldWidth, fieldHeight);
        submitButton.setOnAction((e) -> this.controller.onSubmitClick());

        Text changeScreen = StyleUtils.createText(screenType == ScreenType.LOGIN ? "Регистрация" : "Вход", font,
                Environment.BUTTONS_COLOR);
        changeScreen.setCursor(Cursor.HAND);
        changeScreen.setOnMouseClicked((e) -> {
            this.drawScreen(this.currentScreen == ScreenType.LOGIN ?
                    ScreenType.REGISTER : ScreenType.LOGIN);
            this.errorText.setText("");
        });

        child.addAll(text, this.usernameField, this.passwordField);
        if (screenType == ScreenType.REGISTER) {
            this.passwordRepeatField = StyleUtils.createPasswordField(this.getRepeatPasswordText(), "Повтор пароля",
                    font, fieldWidth, fieldHeight);
            child.add(this.passwordRepeatField);
        }
        child.addAll(submitButton, changeScreen, this.errorText);

        super.setCenter(form);
        this.currentScreen = screenType;
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

    @Override
    public void redraw() {
        this.drawScreen(this.currentScreen);
    }

}