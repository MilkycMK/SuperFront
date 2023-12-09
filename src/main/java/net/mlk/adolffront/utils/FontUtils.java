package net.mlk.adolffront.utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FontUtils {

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

}
