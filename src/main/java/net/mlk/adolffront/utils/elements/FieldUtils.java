package net.mlk.adolffront.utils.elements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.StyleUtils;

public class FieldUtils {

    public static TextField createTextField(String defaultText, String placeholder, Font font) {
        return createTextField(defaultText, placeholder, font, Environment.TEXTFIELD_BACKGROUND);
    }

    public static TextField createTextField(String defaultText, String placeholder, Font font, Background background) {
        TextField newField = new TextField(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        StyleUtils.setTextColor(newField, Color.WHITE);

        return newField;
    }

    public static PasswordField createPasswordField(String placeholder, Font font) {
        PasswordField newField = new PasswordField();
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        StyleUtils.setTextColor(newField, Color.WHITE);

        return newField;
    }

    public static TextArea createTextArea(String defaultText, String placeholder, Font font, Color color) {
        TextArea textArea = new TextArea(defaultText);
        textArea.setPromptText(placeholder);
        textArea.setFont(font);
        StyleUtils.setTextColor(textArea, Color.WHITE);
        StyleUtils.setBackground(textArea, color);

        return textArea;
    }

    public static TextInputControl setMaxTextLength(TextInputControl field, int max) {
        field.textProperty().addListener((observableValue, string, t1) -> {
            if (field.getText().length() > max) {
                field.setText(string);
            }
        });
        return field;
    }

    public static TextInputControl applyOnlyIntegersFilter(TextInputControl field) {
        field.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        return field;
    }

}
