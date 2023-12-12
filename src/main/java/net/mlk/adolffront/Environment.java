package net.mlk.adolffront;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Environment {
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    public static String filePath = isWindows ? System.getenv("LOCALAPPDATA") + "/.mlkit/tk.json" :
            System.getProperty("user.home") + "/.mlkit/tk.json";
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
    public static final Background CORNER_BACKGROUND =
            new Background(new BackgroundFill(Environment.PANELS_COLOR,
                    new CornerRadii(0, Environment.ROUND, 0, 0, false), Insets.EMPTY));
    public static final Background TODO_ACTIVE =
            new Background(new BackgroundFill(Environment.PANELS_COLOR,
                    CornerRadii.EMPTY,  Insets.EMPTY));

    public static final Background MENU_BACKGROUND =
            new Background(new BackgroundFill(PANELS_COLOR, CornerRadii.EMPTY, new Insets(0, 0, 10, 0)));
    public static final Background BUTTON_BACKGROUND =
            new Background(new BackgroundFill(BUTTONS_COLOR, new CornerRadii(ROUND), Insets.EMPTY));
    public static final Background TEXTFIELD_BACKGROUND =
            new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(ROUND), Insets.EMPTY));

    public static final String URL = "http://localhost:8443";

    public static final String LOGIN = URL + "/signin";
    public static final String REGISTER = URL + "/signup";
    public static final String LOGOUT = URL + "/logout";

    public static final String TODO = URL + "/todo";
    public static final String TODO_ELEMENT = TODO + "/%s";
    public static final String TODO_FILE = TODO_ELEMENT + "/files/%s";

    public static String getTodoElementUrl(int id) {
        return String.format(TODO_ELEMENT, id);
    }

    public static String getTodoFileUrl(int id, String fileName) {
        return String.format(TODO_FILE, id, fileName);
    }

    public static final String FINANCE = URL + "/finances";
    public static final String TRANSACTIONS = FINANCE + "/transactions";

    public static final String GROUPS = URL + "/groups";


}
