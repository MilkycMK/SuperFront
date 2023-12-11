package net.mlk.adolffront.screens.menu;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

public abstract class AbstractMenuElement extends BorderPane {
    private final String name;
    private final Text errorText = TextUtils.createText(null, FontUtils.createFont(), Color.RED);

    public AbstractMenuElement(String name) {
        this.errorText.setTextAlignment(TextAlignment.CENTER);
        this.errorText.translateXProperty().bind(super.widthProperty().multiply(0.5));
        this.errorText.translateYProperty().bind(super.heightProperty());
        super.getChildren().add(this.errorText);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void drawScreen() {

    }

    public Text getErrorText() {
        return this.errorText;
    }

    public void setErrorText(String text) {
        this.errorText.setText(text);
    }
}