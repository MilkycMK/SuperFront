package net.mlk.adolffront.utils.elements;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.StyleUtils;

public class ButtonUtils {

    public static Button createButton(String text, Font font) {
        return createButton(text, font, Environment.BUTTON_BACKGROUND);
    }

    public static Button createButton(String text, Font font, Background background) {
        Button button = new Button(text);
        button.setFont(font);
        button.setCursor(Cursor.HAND);
        button.setBackground(background);
        StyleUtils.setTextColor(button, Color.WHITE);
        return button;
    }

}
