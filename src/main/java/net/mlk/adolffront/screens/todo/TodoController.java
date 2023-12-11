package net.mlk.adolffront.screens.todo;

import net.mlk.adolffront.http.AdolfServer;

import java.io.IOException;

public class TodoController {
    private final TodoScreen screen;

    public TodoController(TodoScreen screen) {
        this.screen = screen;
    }

    public void createTodo(TodoElement element) {
        try {
            element.setId(AdolfServer.postTodo(element));
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public void deleteTodo(TodoElement todo) {
        try {
            AdolfServer.deleteTodo(todo.getId());
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public TodoScreen getScreen() {
        return this.screen;
    }

}
