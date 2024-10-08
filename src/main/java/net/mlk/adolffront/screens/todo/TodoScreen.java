package net.mlk.adolffront.screens.todo;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import net.mlk.adolffront.AdolfFront;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.elements.DateTimePicker;
import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TodoScreen extends AbstractMenuElement {
    private Set<TodoElement> todoElements = new HashSet<>();
    private TodoElement currentElement;
    private final TodoController controller;
    private VBox leftPanel;
    private final ScrollPane todoScroll = new ScrollPane();
    private VBox currentBox;

    public TodoScreen() {
        super("Тудушки");
        this.controller = new TodoController(this);
        super.getErrorText().translateXProperty().bind(super.widthProperty().multiply(0.5));
    }

    @Override
    public void drawScreen() {
        if (this.todoElements.isEmpty()) {
            this.updateTodos();
        }
        this.drawLeftMenu();
        this.drawTodoList();
        this.leftPanel.getChildren().add(this.todoScroll);
        this.todoScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void drawLeftMenu() {
        this.leftPanel = new VBox();
        this.leftPanel.setBackground(Environment.CORNER_BACKGROUND);
        this.leftPanel.prefWidthProperty().bind(super.widthProperty().divide(3));
        Font font = FontUtils.createFont();

        VBox buttons = new VBox();
        buttons.spacingProperty().bind(this.leftPanel.heightProperty().multiply(0.04));
        buttons.setPadding(new Insets(20));
        buttons.setAlignment(Pos.CENTER);

        Button create = ButtonUtils.createButton("Создать тудушку", font);
        create.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        create.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        create.setOnMouseClicked((e) -> {
            if (this.currentElement == null || this.currentElement.getId() != -1) {
                TodoElement element = new TodoElement();
                this.openTodo(element);
            }
        });

        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        refresh.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        refresh.setOnMouseClicked((e) -> {
            this.updateTodos();
            this.drawTodoList();
        });

        buttons.getChildren().addAll(create, refresh);
        this.leftPanel.getChildren().add(buttons);
        super.setLeft(this.leftPanel);
    }

    public void drawTodoList() {
        StyleUtils.setBackground(this.todoScroll, Environment.BACKGROUND_COLOR);
        this.todoScroll.prefHeightProperty().bind(this.leftPanel.heightProperty().multiply(0.77));
        VBox todos = new VBox();
        this.todoScroll.setContent(todos);

        for (TodoElement element : this.todoElements) {
            this.addTodo(element, this.currentElement != null && this.currentElement.getId() == element.getId());
        }

    }

    public void drawTodoElement(TodoElement element) {
        VBox todoBox = new VBox();
        todoBox.setPadding(new Insets(0, Environment.width / 20, 0, Environment.width / 20));
        Font topicFont = FontUtils.createFont(FontWeight.BOLD, 20);
        Font font = FontUtils.createFont(14);

        if (element == null) {
            super.setCenter(todoBox);
            return;
        }
        TextArea topic = FieldUtils.createTextArea(TextUtils.truncateString(element.getTopic(), 16), "Заголовок", topicFont, Color.TRANSPARENT);
        topic.setWrapText(true);
        FieldUtils.setMaxTextLength(topic, 128);
        topic.prefWidthProperty().bind(super.widthProperty().multiply(0.8));
        topic.prefHeightProperty().bind(super.heightProperty().multiply(0.25));
        TextArea description = FieldUtils.createTextArea(element.getDescription() == null || element.getDescription().equals("null") ? null :
                element.getDescription(), "Описание", font, Color.TRANSPARENT);
        description.setWrapText(true);
        FieldUtils.setMaxTextLength(description, 3000);
        description.prefWidthProperty().bind(super.widthProperty().multiply(0.8));
        description.prefHeightProperty().bind(super.heightProperty().multiply(0.4));

        HBox control = new HBox();
        control.spacingProperty().bind(super.widthProperty().multiply(0.05));

        DateTimePicker time = new DateTimePicker(element.getTaskTime());
        time.prefWidthProperty().bind(super.widthProperty().multiply(0.35));
        time.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        StyleUtils.setBackground(time, Environment.PANELS_COLOR);

        Button delete = ButtonUtils.createButton("Удалить", font);
        delete.prefWidthProperty().bind(super.widthProperty().multiply(0.25));
        delete.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        delete.setDisable(element.getId() == -1);
        delete.setOnMouseClicked((e) -> {
            this.controller.deleteTodo(element);
            this.todoElements.remove(element);
            ((Pane) this.todoScroll.getContent()).getChildren().remove(this.currentBox);
            this.openTodo(null);
        });

        Button save = ButtonUtils.createButton("Сохранить", font);
        save.prefWidthProperty().bind(super.widthProperty().multiply(0.25));
        save.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        save.setOnMouseClicked((e) -> {
            if (topic.getText() == null || topic.getText().isEmpty()) {
                super.setErrorText("Заголовок не может быть пустым.");
                return;
            }
            super.setErrorText("");
            element.setTopic(topic.getText());
            element.setDescription(description.getText());
            element.setTaskTime(time.getDateTimeValue());
            if (element.getId() != -1) {
                this.controller.updateTodo(element);
                this.drawTodoList();
                return;
            }

            this.controller.createTodo(element);
            this.addTodo(element, true);
            delete.setDisable(false);
        });

        VBox files = new VBox();
        files.setPadding(new Insets(10, 0, 0, 0));
        files.setSpacing(10);
        Text filesText = TextUtils.createText("Файлы", FontUtils.createFont(20));
        FlowPane filesPane = new FlowPane(Orientation.VERTICAL, 5, 2);
        filesPane.maxHeightProperty().bind(todoBox.heightProperty().multiply(0.2));
        for (TodoFile file : element.getFiles()) {
            this.addFilePart(filesPane, file);
        }
        if (filesPane.getChildren().size() < 5) {
            Button newFile = ButtonUtils.createButton("Новый файл", font);
            newFile.minWidthProperty().bind(filesPane.widthProperty().multiply(0.28));
            newFile.minHeightProperty().bind(filesPane.heightProperty().multiply(0.34));
            filesPane.getChildren().add(0, newFile);
            newFile.setOnMouseClicked((e) -> {
                FileChooser chooser = new FileChooser();
                File file = chooser.showOpenDialog(AdolfFront.getStage());
                if (file == null) {
                    return;
                }
                TodoFile todoFile = new TodoFile(file);
                if (element.addFile(todoFile)) {
                    this.addFilePart(filesPane, todoFile);
                }
            });
        }
        files.getChildren().addAll(filesText, filesPane);

        control.getChildren().addAll(time, delete, save);
        todoBox.getChildren().addAll(topic, description, control, files);
        super.setCenter(todoBox);
    }

    private void addFilePart(FlowPane filesPane, TodoFile file) {
        HBox part = new HBox();
        part.spacingProperty().bind(filesPane.widthProperty().multiply(0.009));
        part.setBackground(Environment.PANELS_BACKGROUND);
        part.setPadding(new Insets(5, 15, 5, 15));
        part.setAlignment(Pos.TOP_CENTER);
        Text deleteFile = TextUtils.createText("-", FontUtils.createFont(25), Color.RED);
        deleteFile.setCursor(Cursor.HAND);
        Text name = TextUtils.createText(TextUtils.truncateString(file.getName(), 9), FontUtils.createFont());
        part.getChildren().addAll(name, deleteFile);
        HBox.setMargin(deleteFile, new Insets(-10, 0, 0, 0));
        filesPane.getChildren().add(part);
        name.setCursor(Cursor.HAND);
        name.setOnMouseClicked((e) -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File f = chooser.showDialog(AdolfFront.getStage());
            if (f == null) {
                return;
            }
            if (this.currentElement.getId() != -1) {
                this.controller.saveFile(this.currentElement.getId(), file, f.getAbsolutePath());
            }
        });
        deleteFile.setOnMouseClicked((e) -> {
            this.currentElement.deleteFile(file);
            filesPane.getChildren().remove(part);
            if (filesPane.getChildren().size() <= 5) {
                filesPane.getChildren().get(0).setDisable(false);
            }
        });
        if (filesPane.getChildren().size() > 5) {
            filesPane.getChildren().get(0).setDisable(true);
        }
    }

    private void addTodo(TodoElement element, boolean current) {
        VBox vBox = new VBox();
        vBox.setCursor(Cursor.HAND);
        vBox.minHeightProperty().bind(this.todoScroll.heightProperty().multiply(0.2));
        vBox.minWidthProperty().bind(this.todoScroll.widthProperty());
        vBox.setPadding(new Insets(20));
        Text name = TextUtils.createText(element.getTopic(), FontUtils.createFont(FontWeight.BOLD, 15));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePicker.DefaultFormat);
        Text time = TextUtils.createText(formatter.format(element.getCreationTime()) + " - "
                + formatter.format(element.getTaskTime()), FontUtils.createFont(10));
        vBox.getChildren().addAll(name, time);

        if (current) {
            if (this.currentBox != null) {
                this.currentBox.setBackground(Environment.BACKGROUND);
            }
            this.currentBox = vBox;
            this.currentBox.setBackground(Environment.TODO_ACTIVE);
        }
        vBox.setOnMouseClicked((e) -> {
            if (this.currentBox != null) {
                this.currentBox.setBackground(Environment.BACKGROUND);
            }
            this.currentBox = vBox;
            this.currentBox.setBackground(Environment.TODO_ACTIVE);
            this.openTodo(element);
        });

        ((Pane) this.todoScroll.getContent()).getChildren().add(vBox);
    }

    public void openTodo(TodoElement element) {
        this.currentElement = element;
        this.drawTodoElement(element);
    }

    public void updateTodos() {
        try {
            this.todoElements = AdolfServer.getTodo();
        } catch (IOException ex) {
            super.setErrorText("Ошибка сети.");
        }
    }

}
