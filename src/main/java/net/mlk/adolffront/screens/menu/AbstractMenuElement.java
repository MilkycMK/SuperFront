package net.mlk.adolffront.screens.menu;

import javafx.scene.layout.BorderPane;
import net.mlk.adolffront.utils.IResizable;

public abstract class AbstractMenuElement extends BorderPane implements IResizable {
    private final String name;

    public AbstractMenuElement(String name) {
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
