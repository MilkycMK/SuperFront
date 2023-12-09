package net.mlk.adolffront.utils;

import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;

public class FieldUtils {

    public static TextField createTextField(String defaultText, String placeholder, Font font) {
        return createTextField(defaultText, placeholder, font, Environment.TEXTFIELD_BACKGROUND);
    }

    public static TextField createTextField(String defaultText, String placeholder, Font font, Background background) {
        TextField newField = new TextField(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);

        return newField;
    }

}
