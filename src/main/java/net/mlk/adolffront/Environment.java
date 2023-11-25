package net.mlk.adolffront;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Environment {
    public static final Color BACKGROUND_COLOR = Color.rgb(24, 24, 24, 1.0);
    public static final Color PANELS_COLOR = Color.rgb(33, 33, 33, 1.0);
    public static final Color BUTTONS_COLOR = Color.rgb(106, 82, 218, 1.0);

    public static final Background TRANSPARENT_BACKGROUND =
            new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));
    public static final Background PANELS_BACKGROUND =
            new Background(new BackgroundFill(PANELS_COLOR, new CornerRadii(30), Insets.EMPTY));
    public static final Background BACKGROUND =
            new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(30), Insets.EMPTY));
    public static final Background BUTTON_BACKGROUND =
            new Background(new BackgroundFill(BUTTONS_COLOR, new CornerRadii(30), Insets.EMPTY));

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int ROUND = 20;

    public static Font getFont(int size) {
        return getFont("Inter Bold", size);
    }

    public static Font getFont(String family, int size) {
        return Font.font(family, size);
    }
}
