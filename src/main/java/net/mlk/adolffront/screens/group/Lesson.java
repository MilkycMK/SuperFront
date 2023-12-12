package net.mlk.adolffront.screens.group;

import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.utils.JsonConvertible;

public class Lesson implements JsonConvertible {
    private int id;
    private String name;
    private int hours;
    @JsonField(key = "passed_hours")
    private int passedHours;

    public Lesson() {

    }

    public Lesson(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getHours() {
        return this.hours;
    }

    public int getPassedHours() {
        return this.passedHours;
    }
}
