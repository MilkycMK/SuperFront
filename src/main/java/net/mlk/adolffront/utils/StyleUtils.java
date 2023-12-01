package net.mlk.adolffront.utils;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;

public class StyleUtils {

    public static void setInnerTextColor(Node node, String color) {
        node.setStyle(String.format("-fx-text-inner-color: %s;", color));
    }

    public static void setTextColor(Node node, String color) {
        node.setStyle(String.format("-fx-text-fill: %s;", color));
    }

    public static Text createText(String content) {
        return createText(content, StyleUtils.createFont(15));
    }

    public static Text createText(String content, Font font) {
        return createText(content, font, Color.WHITE);
    }

    public static Text createText(String content, Font font, Color color) {
        Text text = new Text(content);
        text.setFont(font);
        text.setFill(color);
        return text;
    }

    public static Font createFont() {
        return createFont(15);
    }

    public static Font createFont(double size) {
        return createFont("Inter Bold", size);
    }

    public static Font createFont(String family, double size) {
        return Font.font(family, size);
    }

    public static TextField createTextField(String defaultText, String placeholder, Font font, double width,
                                            double height) {
        TextField newField = new TextField(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        newField.setMaxWidth(width);
        newField.setMinHeight(height);
        setInnerTextColor(newField, "white");

        return newField;
    }

    public static PasswordField createPasswordField(String defaultText, String placeholder, Font font, double width,
                                                    double height) {
        PasswordField newField = new PasswordField();
        newField.setText(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        newField.setMaxWidth(width);
        newField.setMinHeight(height);
        setInnerTextColor(newField, "white");

        return newField;
    }

    public static Button createButton(String text, Font font, double width, double height) {
        Button button = new Button(text);
        button.setMaxWidth(width);
        button.setMinHeight(height);
        button.setFont(font);
        button.setCursor(Cursor.HAND);
        button.setBackground(Environment.BUTTON_BACKGROUND);
        setTextColor(button, "white");

        return button;
    }

}
