package net.mlk.adolffront.screens.login;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.FieldUtils;
import net.mlk.adolffront.utils.FontUtils;
import net.mlk.adolffront.utils.TextUtils;

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
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(0.05));
        vBox.setBackground(Environment.PANELS_BACKGROUND);
        vBox.maxWidthProperty().bind(super.widthProperty().multiply(0.5));
        vBox.maxHeightProperty().bind(super.heightProperty().multiply(0.7));
        vBox.prefWidthProperty().bind(super.widthProperty());
        vBox.prefHeightProperty().bind(super.heightProperty());
        vBox.setAlignment(Pos.CENTER);

        Text test = TextUtils.createText(screenType == ScreenType.LOGIN ? "Авторизация" : "Регистрация",
                FontUtils.createFont(30), Color.WHITE);

        Font fieldFont = FontUtils.createFont();

        vBox.getChildren().addAll(test);
        super.setCenter(vBox);
    }

}
