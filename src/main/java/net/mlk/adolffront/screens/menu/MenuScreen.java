package net.mlk.adolffront.screens.menu;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.IResizable;
import net.mlk.adolffront.utils.StyleUtils;

import java.util.ArrayList;
import java.util.Collection;

public class MenuScreen extends BorderPane implements IResizable {
    private final ArrayList<MenuElement> elements = new ArrayList<>();
    private MenuElement currentElement;

    public MenuScreen() {
        this.drawMenu();
    }

    public void drawMenu() {
        double height = Environment.height / 8;
        double elementsWidth = Environment.width / this.elements.size();
        Font font = StyleUtils.createFont(elementsWidth / 18);

        if (this.currentElement == null && !this.elements.isEmpty()) {
            this.currentElement = this.elements.get(0);
        }

        HBox menu = new HBox();
        menu.setMinHeight(height);
        menu.setBackground(Environment.MENU_BACKGROUND);

        for (MenuElement element : this.elements) {
            Background background = Environment.MENU_BACKGROUND;
            if (this.currentElement == element) {
                background = Environment.BACKGROUND;
            }
            Button button = StyleUtils.createButton(element.getName(), font, background, elementsWidth,
                    height - 10);
            button.setOnMouseClicked((e) -> {
                this.currentElement = element;
                this.redraw();
            });
            menu.getChildren().add(button);
        }

        if (this.currentElement != null) {
            this.currentElement.drawScreen();
            super.setCenter(this.currentElement);
        }
        super.setTop(menu);
    }

    public MenuScreen addElement(MenuElement element) {
        this.elements.add(element);
        this.redraw();
        return this;
    }

    public MenuScreen addElements(Collection<MenuElement> elements) {
        this.elements.addAll(elements);
        this.redraw();
        return this;
    }

    @Override
    public void redraw() {
        this.drawMenu();
    }
}
