package net.mlk.adolffront;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;

public class Environment {
    public static String token;
    public static String name;

    public static double width = 800;
    public static double height = 600;
    public static final double ROUND = 30;

    public static final Color BACKGROUND_COLOR = Color.rgb(24, 24, 24, 1.0);
    public static final Color PANELS_COLOR = Color.rgb(33, 33, 33, 1.0);
    public static final Color BUTTONS_COLOR = Color.rgb(106, 82, 218, 1.0);

    public static final Background TRANSPARENT_BACKGROUND =
            new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));
    public static final Background PANELS_BACKGROUND =
            new Background(new BackgroundFill(PANELS_COLOR, new CornerRadii(ROUND), Insets.EMPTY));
    public static final Background BACKGROUND =
            new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY));

    public static final Background NOTIFICATION_BACKGROUND =
            new Background(new BackgroundFill(PANELS_COLOR, new CornerRadii(ROUND, 0, 0, ROUND, false), Insets.EMPTY));
    public static final Background BUTTON_BACKGROUND =
            new Background(new BackgroundFill(BUTTONS_COLOR, new CornerRadii(ROUND), Insets.EMPTY));
    public static final Background TEXTFIELD_BACKGROUND =
            new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(ROUND), Insets.EMPTY));

    public static final String URL = "https://mamamia.mom:8443";
    public static final String LOGIN = URL + "/signin";
    public static final String REGISTER = URL + "/signup";
    public static final String LOGOUT = URL + "/logout";

}
