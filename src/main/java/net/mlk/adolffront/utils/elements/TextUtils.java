package net.mlk.adolffront.utils.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextUtils {

    public static Text createText(String content) {
        return createText(content, FontUtils.createFont());
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

    public static String truncateString(String string, int size) {
        if (string == null || string.length() <= size) {
            return string;
        } else {
            int halfSize = (size) / 2;
            String leftHalf = string.substring(0, halfSize);
            String rightHalf = string.substring(string.length() - halfSize);
            return leftHalf + "..." + rightHalf;
        }
    }

}
