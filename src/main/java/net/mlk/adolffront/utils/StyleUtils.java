package net.mlk.adolffront.utils;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class StyleUtils {

    public static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }

    public static void setTextColor(Node node, Color color) {
        String hex = toHexString(color);
        node.setStyle(node.getStyle() + String.format("-fx-text-fill: %s; -fx-text-inner-color: %s;", hex, hex));
    }

}
