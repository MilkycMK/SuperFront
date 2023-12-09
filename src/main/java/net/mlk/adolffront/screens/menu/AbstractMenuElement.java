package net.mlk.adolffront.screens.menu;

import javafx.scene.layout.BorderPane;

public abstract class AbstractMenuElement extends BorderPane {
    private final String name;

    public AbstractMenuElement(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void drawScreen() {

    }
}