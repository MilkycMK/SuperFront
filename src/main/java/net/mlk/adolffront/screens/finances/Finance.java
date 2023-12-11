package net.mlk.adolffront.screens.finances;

import net.mlk.jmson.annotations.JsonField;
import net.mlk.jmson.annotations.JsonIgnore;
import net.mlk.jmson.utils.JsonConvertible;

import java.time.LocalDate;

public class Finance implements JsonConvertible {
    @JsonIgnore
    private int id;
    private double salary;
    private double remain;
    @JsonField(key = "creation_date", dateFormat = "yyyy-MM-dd")
    @JsonIgnore
    private LocalDate creationDate = LocalDate.now();
    @JsonField(key = "salary_date", dateFormat = "yyyy-MM-dd")
    private LocalDate salaryDate;

    public int getId() {
        return this.id;
    }

    public double getSalary() {
        return this.salary;
    }

    public double getRemain() {
        return this.remain;
    }

    public LocalDate getSalaryDate() {
        return this.salaryDate;
    }
}
