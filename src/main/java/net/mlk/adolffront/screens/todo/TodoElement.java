package net.mlk.adolffront.screens.todo;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.elements.DateTimePicker;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;

import java.time.LocalDateTime;

public class TodoElement extends VBox {
    private final TodoController controller;
    private int id = -1;
    private String topic;
    private String description;
    private LocalDateTime taskTime;

    public TodoElement(TodoController controller) {
        this.controller = controller;
    }

    public void drawElement() {
        super.setPadding(new Insets(0, Environment.width / 20, 0, Environment.width / 20));
        super.spacingProperty().bind(super.heightProperty().multiply(0.05));
        Font topicFont = FontUtils.createFont(FontWeight.BOLD, 20);
        Font font = FontUtils.createFont();

        TextArea topic = FieldUtils.createTextArea(null, "Заголовок", topicFont, Color.TRANSPARENT);
        topic.setWrapText(true);
        FieldUtils.setMaxTextLength(topic, 128);
        topic.prefWidthProperty().bind(super.widthProperty().multiply(0.8));
        topic.prefHeightProperty().bind(super.heightProperty().multiply(0.25));

        TextArea description = FieldUtils.createTextArea(null, "Описание", font, Color.TRANSPARENT);
        description.setWrapText(true);
        FieldUtils.setMaxTextLength(description, 3000);
        description.prefWidthProperty().bind(super.widthProperty().multiply(0.8));
        description.prefHeightProperty().bind(super.heightProperty().multiply(0.4));

        HBox control = new HBox();
        control.spacingProperty().bind(super.widthProperty().multiply(0.05));

        DateTimePicker time = new DateTimePicker();
        time.prefWidthProperty().bind(super.widthProperty().multiply(0.35));
        time.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        StyleUtils.setBackground(time, Environment.PANELS_COLOR);

        Button delete = ButtonUtils.createButton("Удалить", font);
        delete.prefWidthProperty().bind(super.widthProperty().multiply(0.25));
        delete.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        delete.setVisible(this.id != -1);

        Button save = ButtonUtils.createButton("Сохранить", font);
        save.prefWidthProperty().bind(super.widthProperty().multiply(0.25));
        save.minHeightProperty().bind(super.heightProperty().multiply(0.08));
        save.setOnMouseClicked((e) -> {
            this.topic = topic.getText();
            this.description = description.getText();
            this.taskTime = time.getDateTimeValue();
            this.id = this.controller.postTodo(this);
        });

        control.getChildren().addAll(time, delete, save);
        super.getChildren().addAll(topic, description, control);
        Platform.runLater(super::requestFocus);
    }

    public int getElementId() {
        return this.id;
    }

}
