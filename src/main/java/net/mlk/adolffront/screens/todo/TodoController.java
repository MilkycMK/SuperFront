package net.mlk.adolffront.screens.todo;

public class TodoController {
    private final TodoScreen screen;

    public TodoController(TodoScreen screen) {
        this.screen = screen;
    }

    public TodoElement createTodo() {
        TodoElement element = new TodoElement(this);
        this.screen.openTodo(element);

        return element;
    }

    public int postTodo(TodoElement todo) {
        return -1;
    }

}
