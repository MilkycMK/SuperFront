package net.mlk.adolffront.screens.menu;

import javafx.scene.layout.BorderPane;
import net.mlk.adolffront.utils.IResizable;

public class MenuElement extends BorderPane implements IResizable {
    private final String name;

    public MenuElement(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void drawScreen() {

    }

    @Override
    public void redraw() {
        this.drawScreen();
    }
}
