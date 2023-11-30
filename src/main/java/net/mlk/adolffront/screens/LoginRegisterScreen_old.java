//package net.mlk.adolffront.screens;
//
//import javafx.geometry.Pos;
//import javafx.scene.Cursor;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import net.mlk.adolffront.Environment;
//import net.mlk.adolffront.utils.StyleUtils;
//
//import java.io.File;
//
//public class LoginRegisterScreen extends BorderPane {
//    private static final double HEIGHT = Environment.HEIGHT - 200;
//    private static final double WIDTH = Environment.WIDTH - 400;
//    private static final double FIELDS_HEIGHT = HEIGHT / 7;
//    private static final double FIELDS_WIDTH = WIDTH / 2;
//    private enum Type { LOGIN, REGISTER }
//    private Type currentType = Type.LOGIN;
//
//    public LoginRegisterScreen() {
//        super.setBackground(Environment.TRANSPARENT_BACKGROUND);
//        this.drawForm(Type.LOGIN);
//    }
//
//    private void drawForm(Type type) {
//        VBox form = new VBox();
//        form.setBackground(Environment.PANELS_BACKGROUND);
//        form.setAlignment(Pos.CENTER);
//        form.setSpacing(20);
//        form.setMaxHeight(currentType == Type.LOGIN ? HEIGHT : HEIGHT * 1.2);
//        form.setMaxWidth(WIDTH);
//
//        Font fieldFont = StyleUtils.createFont(15);
//        TextField usernameField = this.createTextField("Логин", fieldFont);
//        PasswordField passwordField = this.createPasswordField("Пароль", fieldFont);
//
//        if (type == Type.REGISTER) {
//            Text text = StyleUtils.createText("Регистрация", 30);
//            Button submitButton = this.createButton("Зарегистрироваться", fieldFont, FIELDS_WIDTH, FIELDS_HEIGHT);
//            PasswordField repeatPassword = this.createPasswordField("Повтор Пароля", fieldFont);
//            form.getChildren().addAll(text, usernameField, passwordField, repeatPassword, submitButton);
//        } else {
//            Text text = StyleUtils.createText("Авторизация", 30);
//            Button submitButton = this.createButton("Войти", fieldFont, FIELDS_WIDTH, FIELDS_HEIGHT);
//            form.getChildren().addAll(text, usernameField, passwordField, submitButton);
//        }
//
//        Button toggleButton = this.createToggleButton(fieldFont);
//        form.getChildren().add(toggleButton);
//
//        super.setCenter(form);
//    }
//
//    private Button createToggleButton(Font font) {
//        Button toggleButton = this.createButton(this.currentType == Type.LOGIN ? "Зарегистрироваться"
//                : "Уже есть аккаунт?", font, FIELDS_WIDTH / 1.1, FIELDS_HEIGHT / 2);
//
//        Button loginButton = this.createButton("Вход", )
//
//        toggleButton.setOnAction(event -> {
//            currentType = (currentType == Type.LOGIN) ? Type.REGISTER : Type.LOGIN;
//            drawForm(currentType);
//        });
//
//        return toggleButton;
//    }
//
//    private TextField createTextField(String promptText, Font font) {
//        TextField field = new TextField();
//        field.setPromptText(promptText);
//        field.setFont(font);
//        field.setMaxWidth(FIELDS_WIDTH);
//        field.setMinHeight(FIELDS_HEIGHT);
//        StyleUtils.setInnerTextColor(field, "white");
//        field.setBackground(Environment.BACKGROUND);
//        return field;
//    }
//
//    private PasswordField createPasswordField(String promptText, Font font) {
//        PasswordField field = new PasswordField();
//        field.setPromptText(promptText);
//        field.setFont(font);
//        field.setMaxWidth(FIELDS_WIDTH);
//        field.setMinHeight(FIELDS_HEIGHT);
//        StyleUtils.setInnerTextColor(field, "white");
//        field.setBackground(Environment.BACKGROUND);
//        return field;
//    }
//
//    private Button createButton(String text, Font font, double width, double height) {
//        Button button = new Button(text);
//        button.setMaxWidth(width);
//        button.setMinHeight(height);
//        button.setFont(font);
//        StyleUtils.setTextColor(button, "white");
//        button.setCursor(Cursor.HAND);
//        button.setBackground(Environment.BUTTON_BACKGROUND);
//        return button;
//    }
//
//}
