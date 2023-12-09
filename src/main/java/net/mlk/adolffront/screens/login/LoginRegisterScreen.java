package net.mlk.adolffront.screens.login;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

public class LoginRegisterScreen extends BorderPane {
    public enum ScreenType { LOGIN, REGISTER }
    private final LoginRegisterController controller;
    private final Text errorText = TextUtils.createText(null, FontUtils.createFont(), Color.RED);

    public LoginRegisterScreen() {
        this.controller = new LoginRegisterController(this);
        this.drawScreen(ScreenType.LOGIN);
    }

    public void drawScreen(ScreenType screenType) {
        VBox vBox = new VBox();
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(0.04));
        vBox.setBackground(Environment.PANELS_BACKGROUND);
        vBox.maxWidthProperty().bind(super.widthProperty().multiply(0.5));
        vBox.maxHeightProperty().bind(super.heightProperty().multiply(0.7));
        vBox.prefWidthProperty().bind(super.widthProperty());
        vBox.prefHeightProperty().bind(super.heightProperty());
        vBox.setAlignment(Pos.CENTER);

        this.errorText.wrappingWidthProperty().bind(vBox.widthProperty().multiply(0.8));
        this.errorText.setTextAlignment(TextAlignment.CENTER);
        this.setErrorText(null);

        Text test = TextUtils.createText(screenType == ScreenType.LOGIN ? "Авторизация" : "Регистрация",
                FontUtils.createFont(30), Color.WHITE);

        Font fieldFont = FontUtils.createFont();

        TextField login = FieldUtils.createTextField(null, "Логин", fieldFont);
        login.maxWidthProperty().bind(vBox.widthProperty().multiply(0.5));
        login.minHeightProperty().bind(vBox.heightProperty().multiply(0.1));
        FieldUtils.setMaxTextLength(login, 32);

        PasswordField password = FieldUtils.createPasswordField("Пароль", fieldFont);
        password.maxWidthProperty().bind(vBox.widthProperty().multiply(0.5));
        password.minHeightProperty().bind(vBox.heightProperty().multiply(0.1));

        PasswordField repeatPassword = FieldUtils.createPasswordField("Повтор пароля", fieldFont);
        repeatPassword.maxWidthProperty().bind(vBox.widthProperty().multiply(0.5));
        repeatPassword.minHeightProperty().bind(vBox.heightProperty().multiply(0.1));

        Button submit = ButtonUtils.createButton(screenType == ScreenType.LOGIN ? "Войти" : "Зарегистрироваться",
                fieldFont);
        submit.maxWidthProperty().bind(vBox.widthProperty().multiply(0.5));
        submit.minHeightProperty().bind(vBox.heightProperty().multiply(0.1));
        submit.setOnMouseClicked((e) -> this.controller.submit(screenType, login.getText(),
                password.getText(), repeatPassword.getText()));

        Text changeText = TextUtils.createText(screenType == ScreenType.LOGIN ? "Регистрация" : "Вход", fieldFont,
                Environment.BUTTONS_COLOR);
        changeText.setCursor(Cursor.HAND);
        changeText.setOnMouseClicked((e) -> {
            this.drawScreen(screenType == ScreenType.LOGIN ? ScreenType.REGISTER : ScreenType.LOGIN);
        });

        vBox.getChildren().addAll(test, login, password, submit, changeText, this.errorText);

        if (screenType == ScreenType.REGISTER) {
            vBox.getChildren().add(3, repeatPassword);
        }
        super.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                this.controller.submit(screenType, login.getText(), password.getText(), repeatPassword.getText());
            }
        });

        super.setCenter(vBox);
    }

    public void setErrorText(String text) {
        this.errorText.setText(text);
    }

}
