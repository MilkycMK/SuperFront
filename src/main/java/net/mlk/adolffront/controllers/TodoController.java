package net.mlk.adolffront.controllers;

import net.mlk.adolffront.screens.todo.TodoElement;
import net.mlk.adolffront.screens.todo.TodoScreen;

public class TodoController {
    private final TodoScreen screen;

    public TodoController(TodoScreen screen) {
        this.screen = screen;
    }

    public TodoElement createTodo() {
        TodoElement element = new TodoElement();
        this.screen.setCenter(element);

        return element;
    }

}
