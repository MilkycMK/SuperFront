package net.mlk.adolffront.screens.todo;

import net.mlk.adolffront.controllers.TodoController;
import net.mlk.adolffront.screens.menu.MenuElement;

public class TodoScreen extends MenuElement {
    private final TodoController controller;

    public TodoScreen() {
        super("Заметки");
        this.controller = new TodoController(this);
    }

    @Override
    public void drawScreen() {
        this.drawTodoList();

    }

    private void drawTodoList() {
//        double width = Environment.width / 4;
//        Font font = StyleUtils.createFont(width / 15);
//
//        BorderPane mainScreen = new BorderPane();
//
//        VBox left = new VBox();
//        left.setBackground(Environment.PANELS_BACKGROUND);
//        left.setMinWidth(width);
//        HBox buttons = new HBox();
//        Button createButton = StyleUtils.createButton("Создать", font, width, width / 6);
//        Button refreshButton = StyleUtils.createButton("Обновить", font, width, width / 6);
//        buttons.getChildren().addAll(createButton, refreshButton);
//        left.getChildren().add(buttons);
//        mainScreen.setLeft(left);

//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setMinWidth(width);
//        scrollPane.setMaxWidth(width);
//        StyleUtils.setBackground(scrollPane, Environment.PANELS_COLOR);
//
//        BorderPane vBox = new BorderPane();
//        scrollPane.setContent(vBox);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//
//
//        vBox.setTop(submitButton);


//        super.setCenter(mainScreen);
    }
}
