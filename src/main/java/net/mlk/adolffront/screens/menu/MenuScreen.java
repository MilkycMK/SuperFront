package net.mlk.adolffront.screens.menu;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FontUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuScreen extends BorderPane {
    private final ArrayList<AbstractMenuElement> elements = new ArrayList<>();
    private AbstractMenuElement currentElement;

    public MenuScreen() {
        this.drawMenu();
    }

    public void drawMenu() {
        Font font = FontUtils.createFont();

        if (this.currentElement == null && !this.elements.isEmpty()) {
            this.currentElement = this.elements.get(0);
        }

        HBox menu = new HBox();
        menu.minHeightProperty().bind(super.heightProperty().multiply(0.15));
        menu.setBackground(Environment.MENU_BACKGROUND);

        for (AbstractMenuElement element : this.elements) {
            Background background = Environment.MENU_BACKGROUND;
            if (this.currentElement == element) {
                background = Environment.BACKGROUND;
            }
            Button button = ButtonUtils.createButton(element.getName(), font, background);
            button.prefWidthProperty().bind(menu.widthProperty().divide(this.elements.size()));
            button.maxHeightProperty().bind(menu.heightProperty());
            button.setOnMouseClicked((e) -> {
                if (element == this.currentElement) {
                    return;
                }
                this.currentElement = element;
                this.drawMenu();
            });
            menu.getChildren().add(button);
        }

        if (this.currentElement != null) {
            this.currentElement.drawScreen();
            super.setCenter(this.currentElement);
        }
        super.setTop(menu);
    }

    public MenuScreen addElement(AbstractMenuElement element) {
        return this.addElements(element);
    }

    public MenuScreen addElements(AbstractMenuElement... elements) {
        this.elements.addAll(Arrays.asList(elements));
        this.drawMenu();
        return this;
    }

}