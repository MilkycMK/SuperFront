package net.mlk.adolffront.screens.todo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.controls.datetime.DateTimePicker;
import net.mlk.adolffront.utils.IResizable;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoElement extends BorderPane implements JsonConvertible, IResizable {
    private static final int TOPIC_LENGTH = 128;
    private int id = -1;
    private String topic;
    private String description;
    @JsonField(key = "creation_time")
    private LocalDateTime creationTime;
    @JsonField(key = "task_time")
    private LocalDateTime taskTime;
    private ArrayList<TodoFile> files;

    public TodoElement() {
        this.drawElement();
    }

    public void drawElement() {
        super.setPadding(new Insets(Environment.width / 20));
        double width = Environment.width / 1.8;
        Font topicFont = StyleUtils.createFont(FontWeight.BOLD, width / 20);
        Font font = StyleUtils.createFont(width / 30);

        VBox form = new VBox();
        form.setSpacing(Environment.height / 50);
        TextArea headerArea = StyleUtils.createTextArea(null, null, topicFont, Environment.PANELS_COLOR, width, width / 3.5);
        headerArea.setWrapText(true);
        StyleUtils.setMaxTextLength(headerArea, 128);
        TextArea descriptionArea = StyleUtils.createTextArea(null, null, font, Environment.PANELS_COLOR, width, width / 2);
        StyleUtils.setMaxTextLength(headerArea, 3000);
        descriptionArea.setWrapText(true);

        HBox list = new HBox();
        list.setSpacing(width / 30);
        DatePicker picker = new DatePicker();
        picker.setMinHeight(width / 8);
        picker.setMaxWidth(width / 2);
        StyleUtils.setBackground(picker, Environment.PANELS_COLOR);
        Button save = StyleUtils.createButton("Сохранить", font, width / 4, width / 8);
        Button delete = StyleUtils.createButton("Удалить", font, width / 4, width / 8);
        list.getChildren().addAll(picker, delete, save);

        form.getChildren().addAll(headerArea, descriptionArea, list);
        super.setTop(form);
    }

    public String getTopic() {
        return this.topic;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public LocalDateTime getTaskTime() {
        return this.taskTime;
    }

    public ArrayList<TodoFile> getFiles() {
        return this.files;
    }

    public int getElementId() {
        return this.id;
    }

    @Override
    public void redraw() {
        this.drawElement();
    }

    public static class TodoFile implements JsonConvertible {
        private int id;
        private String name;

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }
}
