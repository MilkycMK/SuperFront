package net.mlk.adolffront.screens.todo;

public class TodoController {
    private TodoScreen screen;

    public TodoController(TodoScreen screen) {
        this.screen = screen;
    }

    public TodoElement createTodo() {
        TodoElement element = new TodoElement();
        this.screen.openTodo(element);

        return element;
    }

}
