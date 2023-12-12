package net.mlk.adolffront.screens.group;

import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDate;

public class LessonHistory implements JsonConvertible {
    private int id;
    private int number;
    @JsonField(dateFormat = "yyyy-MM-dd")
    private LocalDate date;
    private String topic;

    public LessonHistory() {

    }

    public LessonHistory(String topic, LocalDate date) {
        this.topic = topic;
        this.date = date;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTopic() {
        return this.topic;
    }
}
