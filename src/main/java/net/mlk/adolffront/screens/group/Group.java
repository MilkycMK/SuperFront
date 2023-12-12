package net.mlk.adolffront.screens.group;

import net.mlk.adolffront.http.AdolfServer;
import net.mlk.jmson.annotations.JsonIgnore;
import net.mlk.jmson.utils.JsonConvertible;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group implements JsonConvertible {
    @JsonIgnore
    private int id = -1;
    private String name;

    public Group() {

    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}
