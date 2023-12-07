package net.mlk.adolffront.screens.todo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.controllers.TodoController;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.StyleUtils;

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
        this.drawTodoList();
    }

    private void drawTodoList() {
        double width = Environment.width / 3;
        Font font = StyleUtils.createFont(width / 15);

        VBox left = new VBox();
        left.setBackground(TODO_LIST_BACKGROUND);
        left.setMinWidth(width);
        left.setMaxWidth(width);

        VBox buttons = new VBox();
        buttons.setPadding(new Insets(width * 0.05));
        buttons.setSpacing(width * 0.05);
        buttons.setAlignment(Pos.CENTER);

        double buttonWidth = width / 1.5;
        Font buttonFont = StyleUtils.createFont(buttonWidth / 13);
        Button createButton = StyleUtils.createButton("Создать Тудушку", buttonFont, buttonWidth, buttonWidth / 6);
        createButton.setOnMouseClicked((e) -> {
            if (this.currentTodo == null || this.currentTodo.getElementId() != -1) {
                this.currentTodo = this.controller.createTodo();
                this.todoElements.add(this.currentTodo);
            }
        });
        Button refreshButton = StyleUtils.createButton("Обновить Список", buttonFont, buttonWidth, buttonWidth / 6);
        buttons.getChildren().addAll(createButton, refreshButton);
        left.getChildren().add(buttons);
//
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setMinWidth(width);
//        scrollPane.setMaxWidth(width);
//        StyleUtils.setBackground(scrollPane, Environment.PANELS_COLOR);
//
//        BorderPane vBox = new BorderPane();
//        scrollPane.setContent(vBox);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        super.setLeft(left);
    }

    @Override
    public void redraw() {
        this.drawScreen();
        if (this.currentTodo != null) {
            this.currentTodo.redraw();
        }
    }

}
