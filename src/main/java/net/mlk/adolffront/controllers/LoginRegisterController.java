package net.mlk.adolffront.controllers;

import net.mlk.adolffront.screens.LoginRegisterScreen;

public class LoginRegisterController {
    private final LoginRegisterScreen screen;

    public LoginRegisterController(LoginRegisterScreen screen) {
        this.screen = screen;
    }

    public void onSubmitClick() {
        LoginRegisterScreen.ScreenType currentType = this.screen.getCurrentScreen();
        String name = this.screen.getNameText();
        String password = this.screen.getPasswordText();
        String repeatPassword = this.screen.getRepeatPasswordText();
    }

}
