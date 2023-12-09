package net.mlk.adolffront.screens.todo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import java.util.ArrayList;

public class TodoScreen extends AbstractMenuElement {
    private static final Background TODO_LIST_BACKGROUND =
            new Background(new BackgroundFill(Environment.PANELS_COLOR,
                    new CornerRadii(0, Environment.ROUND, 0, 0, false), Insets.EMPTY));
    private final ArrayList<TodoElement> todoElements = new ArrayList<>();
    private TodoElement currentTodo;
    private final TodoController controller;

    public TodoScreen() {
        super("Тудушки");
        this.controller = new TodoController(this);
    }

    @Override
    public void drawScreen() {
        VBox leftPanel = new VBox();
        leftPanel.setBackground(TODO_LIST_BACKGROUND);
        leftPanel.prefWidthProperty().bind(super.widthProperty().divide(3));
        Font font = FontUtils.createFont();

        VBox buttons = new VBox();
        buttons.spacingProperty().bind(leftPanel.heightProperty().multiply(0.04));
        buttons.setPadding(new Insets(20));
        buttons.setAlignment(Pos.CENTER);

        Button create = ButtonUtils.createButton("Создать тудушку", font);
        create.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        create.minHeightProperty().bind(buttons.heightProperty().multiply(0.3));
        create.setOnMouseClicked((e) -> {
            if (this.currentTodo == null || this.currentTodo.getElementId() != -1) {
                this.currentTodo = this.controller.createTodo();
                this.todoElements.add(this.currentTodo);
                this.drawScreen();
            }
        });

        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        refresh.minHeightProperty().bind(buttons.heightProperty().multiply(0.3));

        buttons.getChildren().addAll(create, refresh);
        leftPanel.getChildren().addAll(buttons);
        super.setLeft(leftPanel);
    }

    public void openTodo(TodoElement element) {
        this.currentTodo = element;
        this.currentTodo.drawElement();
        super.setCenter(this.currentTodo);
    }

}
