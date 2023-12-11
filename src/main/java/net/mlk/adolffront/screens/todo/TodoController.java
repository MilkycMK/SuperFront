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

    public void saveFile(int id, TodoFile file, String folder) {
        try {
            AdolfServer.saveFile(id, file, folder);
        } catch (IOException e) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public void updateTodo(TodoElement todo) {
        try {
            AdolfServer.updateTodo(todo);
        } catch (IOException e) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public TodoScreen getScreen() {
        return this.screen;
    }

}
