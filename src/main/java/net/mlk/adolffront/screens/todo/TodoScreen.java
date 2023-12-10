package net.mlk.adolffront.screens.todo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.elements.DateTimePicker;
import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TodoScreen extends AbstractMenuElement {
    private static final Background TODO_LIST_BACKGROUND =
            new Background(new BackgroundFill(Environment.PANELS_COLOR,
                    new CornerRadii(0, Environment.ROUND, 0, 0, false), Insets.EMPTY));
    private List<TodoElement> todoElements = new ArrayList<>();
    private TodoElement currentTodo;
    private final TodoController controller;

    public TodoScreen() {
        super("Тудушки");
        this.controller = new TodoController(this);
    }

    @Override
    public void drawScreen() {
        if (this.todoElements.isEmpty()) {
            try {
                this.todoElements = AdolfServer.getTodo();
            } catch (IOException ignored) { }
        }
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
        create.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        create.setOnMouseClicked((e) -> {
            if (this.currentTodo == null || this.currentTodo.getElementId() != -1) {
                this.currentTodo = this.controller.createTodo();
                this.todoElements.add(this.currentTodo);
                this.drawScreen();
            }
        });

        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        refresh.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        refresh.setOnMouseClicked((a) -> {
            try {
                this.todoElements = AdolfServer.getTodo();
                this.drawScreen();
            } catch (IOException ignored) { }
        });


        ScrollPane todoScroll = new ScrollPane();
        StyleUtils.setBackground(todoScroll, Environment.BACKGROUND_COLOR);
        VBox todos = new VBox();

        for (TodoElement element : this.todoElements) {
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 0, 0, 15));
            vBox.minHeightProperty().bind(leftPanel.heightProperty().multiply(0.1));
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setCursor(Cursor.HAND);
            vBox.setOnMouseClicked((a) -> this.openTodo(element));

            Text topic = TextUtils.createText(element.getTopic(),  FontUtils.createFont(FontWeight.BOLD, 15));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePicker.DefaultFormat);
            if (element.getElementId() != -1) {
                String timeString = formatter.format(element.getCreationTime()) + " - " + formatter.format(element.getTaskTime());
                Text time = TextUtils.createText(timeString, FontUtils.createFont(9));
                vBox.getChildren().addAll(topic, time);
            } else {
                vBox.getChildren().addAll(topic);
            }
            todos.getChildren().add(vBox);
        }

        todoScroll.setContent(todos);
        buttons.getChildren().addAll(create, refresh, todoScroll);

        leftPanel.getChildren().addAll(buttons);
        super.setLeft(leftPanel);
    }

    public void openTodo(TodoElement element) {
        if (this.currentTodo == element) {
            return;
        }
        if (this.currentTodo != null && currentTodo.getElementId() == -1) {
            this.todoElements.remove(this.currentTodo);
            this.drawScreen();
        }
        this.currentTodo = element;
        element.drawElement();
        super.setCenter(element);
    }

    public void deleteTodo(TodoElement element) {
        this.todoElements.remove(element);
        this.currentTodo = null;
        this.drawScreen();
        super.setCenter(null);
    }

}
