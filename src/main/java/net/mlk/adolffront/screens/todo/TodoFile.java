package net.mlk.adolffront.screens.todo;

import net.mlk.jmson.utils.JsonConvertible;

import java.io.File;

public class TodoFile implements JsonConvertible {
    private int id = -1;
    private String name;
    private File file;

    public TodoFile() {

    }

    public TodoFile(File file) {
        this.file = file;
        this.name = file.getName();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }
}
