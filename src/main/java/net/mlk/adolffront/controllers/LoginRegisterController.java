package net.mlk.adolffront.controllers;

import javafx.scene.input.KeyCode;
import net.mlk.adolffront.screens.LoginRegisterScreen;

import java.util.regex.Pattern;

public class LoginRegisterController {
    private final LoginRegisterScreen screen;
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9-_]+$");
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 32;
    private static final int MIN_PASSWORD_LENGTH = 6;

    public LoginRegisterController(LoginRegisterScreen screen) {
        this.screen = screen;
        screen.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                this.onSubmitClick();
            }
        });
    }

    public void onSubmitClick() {
        LoginRegisterScreen.ScreenType currentType = this.screen.getCurrentScreen();
        String name = this.screen.getNameText();
        String password = this.screen.getPasswordText();
        String repeatPassword = this.screen.getRepeatPasswordText();

        if (currentType == LoginRegisterScreen.ScreenType.REGISTER) {
            if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH
                    || !LOGIN_PATTERN.matcher(name).matches()) {
                this.screen.setErrorText("Логин должен быть длиной от 4 до 32 символов и состоять из латинских букв и цифр");
                return;
            } else if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
                this.screen.setErrorText("Длина пароля должна быть больше 6 символов");
                return;
            } else if (!password.equals(repeatPassword)) {
                this.screen.setErrorText("Пароли не совпадают.");
                return;
            }

        } else {
            if (name == null) {
                this.screen.setErrorText("Введите логин");
                return;
            } else if (password == null) {
                this.screen.setErrorText("Введите пароль");
                return;
            }

        }
    }

}
