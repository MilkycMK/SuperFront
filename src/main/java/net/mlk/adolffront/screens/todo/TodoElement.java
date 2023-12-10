package net.mlk.adolffront.screens.todo;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import net.mlk.adolffront.AdolfFront;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.elements.DateTimePicker;
import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;
import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.annotations.JsonIgnore;
import net.mlk.jmson.utils.JsonConvertible;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoElement extends VBox implements JsonConvertible {
    @JsonIgnore
    private int id = -1;
    private String topic;
    private String description;
    @JsonIgnore
    @JsonField(key = "creation_time")
    private LocalDateTime creationTime;
    @JsonField(key = "task_time")
    private LocalDateTime taskTime;
    @JsonIgnore
    private final List<TodoFile> files = new ArrayList<>();
    @JsonIgnore
    private HBox filesField;

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
            this.id = AdolfServer.postTodo(this);
        });
        control.getChildren().addAll(time, delete, save);

        this.filesField = new HBox();
        this.filesField.setAlignment(Pos.CENTER_LEFT);
        this.filesField.spacingProperty().bind(super.widthProperty().multiply(0.005));
        Text plus = TextUtils.createText("+", FontUtils.createFont(20));
        plus.setCursor(Cursor.HAND);
        plus.setOnMouseClicked((e) -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(AdolfFront.getStage());
            if (file != null) {
                this.addFile(file);
            }
        });
        this.filesField.getChildren().add(plus);

        super.getChildren().addAll(topic, description, control, this.filesField);
        Platform.runLater(super::requestFocus);
    }

    public void addFile(File file) {
        HBox part = new HBox();
        part.spacingProperty().bind(this.filesField.widthProperty().multiply(0.009));
        part.setBackground(Environment.PANELS_BACKGROUND);
        part.setPadding(new Insets(5, 15, 5, 15));
        Text name = TextUtils.createText(cropString(file.getName(), 3), FontUtils.createFont());
        Text delete = TextUtils.createText("-", FontUtils.createFont(20), Color.RED);
        delete.setTranslateY(-3);
        name.setTranslateY(1);
        part.getChildren().addAll(name, delete);

        ObservableList<Node> child = this.filesField.getChildren();
        child.add(0, part);
        if (child.size() >= 6) {
            child.get(child.size() - 1).setVisible(false);
        }
    }

    public static String cropString(String str, int size) {
        int strSize = str.length();

        if (strSize <= size) {
            return str;
        }
        int start = size / 2;
        int end = size + start;
        return str.substring(0, start) + ".." + str.substring(strSize - end);
    }

    public int getElementId() {
        return this.id;
    }

}
