package net.mlk.adolffront.screens.login;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.FontUtils;

import java.awt.*;

public class LoginRegisterScreen extends BorderPane {
    public enum ScreenType { LOGIN, REGISTER }
    private ScreenType currentScreen;
    private final LoginRegisterController controller;

    public LoginRegisterScreen() {
        this.controller = new LoginRegisterController(this);
        this.drawScreen(ScreenType.LOGIN);
    }

    public void drawScreen(ScreenType screenType) {
        VBox vBox = new VBox();
        vBox.setBackground(Environment.PANELS_BACKGROUND);
        vBox.maxWidthProperty().bind(super.widthProperty().multiply(0.5));
        vBox.maxHeightProperty().bind(super.heightProperty().multiply(0.7));
        vBox.prefWidthProperty().bind(super.widthProperty());
        vBox.prefHeightProperty().bind(super.heightProperty());

        Text test = new Text("test");
//        test.setFont(FontUtils.createFont());
        super.setCenter(test);
    }

}
