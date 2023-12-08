package net.mlk.adolffront.utils;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;

public class StyleUtils {

    public static void setTextColor(Node node, String color) {
        node.setStyle(String.format("-fx-text-fill: %s; -fx-text-inner-color: %s;", color, color, color));
    }

    public static void setBackground(Node node, Color color) {
        String hexColor = toHexString(color);
        node.setStyle(String.format("-fx-control-inner-background: %s; -fx-background: %s; -fx-background-color: transparent;" +
                " -fx-focus-color: transparent; -fx-faint-focus-color: transparent;", hexColor, hexColor));
    }

    public static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
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
        return createFont("Inter", size);
    }

    public static Font createFont(String family, double size) {
        return createFont(family, FontWeight.NORMAL, size);
    }

    public static Font createFont(FontWeight weight, double size) {
        return Font.font("Inter", weight, size);
    }

    public static Font createFont(String family, FontWeight fontWeight, double size) {
        return Font.font(family, fontWeight, size);
    }

    public static TextField createTextField(String defaultText, String placeholder, Font font, double width,
                                            double height) {
        return createTextField(defaultText, placeholder, font, Environment.TEXTFIELD_BACKGROUND, width, height);
    }

    public static TextField createTextField(String defaultText, String placeholder, Font font, Background background,
                                            double width, double height) {
        TextField newField = new TextField(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        newField.setMaxWidth(width);
        newField.setMaxHeight(height);
        newField.setPrefHeight(height);
        newField.setPrefWidth(width);
        newField.setMinWidth(width);
        newField.setMinHeight(height);
        setTextColor(newField, "white");

        return newField;
    }

    public static TextArea createTextArea(String defaultText, String placeholder, Font font, Color background,
                                          double width, double height) {
        TextArea textArea = new TextArea(defaultText);
        textArea.setPromptText(placeholder);
        textArea.setFont(font);
        textArea.setMaxSize(width, height);
        textArea.setMinSize(width, height);
        setTextColor(textArea, "white");
        setBackground(textArea, background);

        return textArea;
    }

    public static TextInputControl setMaxTextLength(TextInputControl field, int max) {
        field.textProperty().addListener((observableValue, string, t1) -> {
            if (field.getText().length() > max) {
                field.setText(field.getText(0, max));
            }
        });
        return field;
    }

//    public static TextInputControl setAutoHeight(TextInputControl field) {
//        field.textProperty().addListener((observableValue, string, t1) -> {
//            int numLines = (int) Math.ceil((double) t1.length() / (field.getWidth() / 16.6));
//            double newHeight = numLines * field.getFont().getSize();
//
//            if (!t1.isEmpty() && t1.charAt(t1.length() - 1) == '\n') {
//                newHeight += field.getFont().getSize();
//            }
//
//            field.setMinHeight(newHeight * 1.75);
//        });
//        return field;
//    }

    public static PasswordField createPasswordField(String defaultText, String placeholder, Font font, double width,
                                                    double height) {
        PasswordField newField = new PasswordField();
        newField.setText(defaultText);
        newField.setPromptText(placeholder);
        newField.setBackground(Environment.TEXTFIELD_BACKGROUND);
        newField.setFont(font);
        newField.setMaxWidth(width);
        newField.setMinHeight(height);
        setTextColor(newField, "white");

        return newField;
    }

    public static Button createButton(String text, Font font, double width, double height) {
        return createButton(text, font, Environment.BUTTON_BACKGROUND, width, height);
    }

    public static Button createButton(String text, Font font, Background background, double width, double height) {
        Button button = new Button(text);
        button.setMinWidth(width);
        button.setMinHeight(height);
        button.setFont(font);
        button.setCursor(Cursor.HAND);
        button.setBackground(background);
        setTextColor(button, "white");
        return button;
    }

}
