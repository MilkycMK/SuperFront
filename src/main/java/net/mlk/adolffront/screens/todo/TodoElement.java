package net.mlk.adolffront.screens.todo;

import javafx.scene.layout.HBox;
import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoElement extends HBox implements JsonConvertible {
    private int id;
    private String topic;
    private String description;
    @JsonField(key = "creation_time")
    private LocalDateTime creationTime;
    @JsonField(key = "task_time")
    private LocalDateTime taskTime;
    private ArrayList<TodoFile> files;

    public TodoElement() { }

    public String getTopic() {
        return this.topic;
    }

    public TodoElement setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public TodoElement setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public LocalDateTime getTaskTime() {
        return this.taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    public ArrayList<TodoFile> getFiles() {
        return this.files;
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
