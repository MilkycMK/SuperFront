package net.mlk.adolffront.screens.finances;

import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.annotations.JsonIgnore;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDate;

public class Transaction implements JsonConvertible {
    public enum Type { ADD, SPEND }
    @JsonIgnore
    private int id;
    private String type;
    @JsonIgnore
    private double salary;
    private double value;
    @JsonIgnore
    private double remain;
    private String description;
    @JsonIgnore
    @JsonField(key = "creation_date", dateFormat = "yyyy-MM-dd")
    private LocalDate creationDate;
    @JsonIgnore
    @JsonField(key = "salary_date", dateFormat = "yyyy-MM-dd")
    private LocalDate salaryDate;

    public Transaction() {

    }

    public Transaction(Finance finance, Type type, double value, String description) {
        this.type = type.toString();
        this.salary = finance.getSalary();
        this.value = value;
        this.remain = finance.getRemain();
        this.description = description;
        this.creationDate = LocalDate.now();
        this.salaryDate = finance.getSalaryDate();
    }

    public int getId() {
        return this.id;
    }

    public double getSalary() {
        return this.salary;
    }

    public double getRemain() {
        return this.remain;
    }

    public double getValue() {
        return this.value;
    }

    public Type getType() {
        if (this.type.equalsIgnoreCase("add")) {
            return Type.ADD;
        } else {
            return Type.SPEND;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public LocalDate getSalaryDate() {
        return this.salaryDate;
    }
}
