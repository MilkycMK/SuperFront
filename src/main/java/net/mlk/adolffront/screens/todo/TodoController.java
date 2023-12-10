package net.mlk.adolffront.screens.todo;

public class TodoController {
    private final TodoScreen screen;

    public TodoController(TodoScreen screen) {
        this.screen = screen;
    }

    public TodoElement createTodo() {
        TodoElement element = new TodoElement();
        this.screen.openTodo(element);
        return element;
    }

    public TodoScreen getScreen() {
        return this.screen;
    }

}
