package net.mlk.adolffront.screens.todo;

import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.annotations.JsonIgnore;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoElement implements JsonConvertible {
    @JsonIgnore
    private int id = -1;
    private String topic;
    private String description;
    @JsonIgnore
    @JsonField(key = "creation_time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime = LocalDateTime.now();
    @JsonField(key = "task_time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime taskTime = LocalDateTime.now();
    @JsonIgnore
    private final List<TodoFile> files = new ArrayList<>();
    @JsonIgnore
    private final List<TodoFile> deletedFiles = new ArrayList<>();
    @JsonIgnore
    private final List<TodoFile> newFiles = new ArrayList<>();

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public boolean addFile(TodoFile file) {
        for (TodoFile todoFile : this.files) {
            if (file.getName().equals(todoFile.getName())) {
                return false;
            }
        }
        this.newFiles.add(file);
        this.files.add(file);
        return true;
    }

    public void deleteFile(TodoFile file) {
        this.deletedFiles.add(file);
        this.files.remove(file);
    }

    public int getId() {
        return this.id;
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

    public List<TodoFile> getFiles() {
        return this.files;
    }

    public List<TodoFile> getNewFiles() {
        return this.newFiles;
    }

    public List<TodoFile> getDeletedFiles() {
        return this.deletedFiles;
    }
}
