package net.mlk.adolffront.screens.login;

import net.mlk.adolffront.AdolfFront;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.http.MultiPartRequest;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginRegisterController {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9-_]+$");
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 32;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private final LoginRegisterScreen screen;

    public LoginRegisterController(LoginRegisterScreen screen) {
        this.screen = screen;
    }

    public void submit(LoginRegisterScreen.ScreenType screenType, String login, String password, String repeatPassword) {
        try {
            if (screenType == LoginRegisterScreen.ScreenType.REGISTER) {
                this.register(login, password, repeatPassword);
            } else {
                this.login(login, password);
            }
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка соединения.");
        }
    }

    public void register(String login, String password, String repeatPassword) throws IOException {
        if (login == null || login.length() < MIN_NAME_LENGTH || login.length() > MAX_NAME_LENGTH
                || !LOGIN_PATTERN.matcher(login).matches()) {
            this.screen.setErrorText("Логин должен быть длиной от 4 до 32 символов и состоять из латинских букв и цифр");
            return;
        } else if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            this.screen.setErrorText("Длина пароля должна быть больше 6 символов");
            return;
        } else if (!password.equals(repeatPassword)) {
            this.screen.setErrorText("Пароли не совпадают.");
            return;
        }

        MultiPartRequest.Response response = AdolfServer.makeRegisterRequest(login, password);
        if (response.getResponseCode() == 409) {
            this.screen.setErrorText("Пользователь уже существует.");
            return;
        }
        AdolfFront.loadUserProfile(response.saveFile(Environment.filePath));
    }

    public void login(String login, String password) throws IOException {
        if (login == null) {
            this.screen.setErrorText("Введите логин");
            return;
        } else if (password == null) {
            this.screen.setErrorText("Введите пароль");
            return;
        }

        MultiPartRequest.Response response = AdolfServer.makeLoginRequest(login, password);
        if (response.getResponseCode() == 401) {
            this.screen.setErrorText("Неверный логин или пароль");
            return;
        }
        AdolfFront.loadUserProfile(response.saveFile(Environment.filePath));
    }

}
