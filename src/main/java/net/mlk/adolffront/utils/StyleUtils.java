package net.mlk.adolffront.utils;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StyleUtils {

    public static void setInnerTextColor(Node node, String color) {
        node.setStyle(String.format("-fx-text-inner-color: %s;", color));
    }

    public static void setTextColor(Node node, String color) {
        node.setStyle(String.format("-fx-text-fill: %s;", color));
    }

    public static Text createText(String content) {
        return createText(content, 15);
    }

    public static Text createText(String content, int size) {
        return createText(content, size, Color.WHITE);
    }

    public static Text createText(String content, int size, Color color) {
        Text text = new Text(content);
        text.setFont(StyleUtils.createFont(size));
        text.setFill(Color.WHITE);
        return text;
    }

    public static Font createFont() {
        return createFont(15);
    }

    public static Font createFont(int size) {
        return createFont("Inter Bold", size);
    }

    public static Font createFont(String family, int size) {
        return Font.font(family, size);
    }

}
