package net.mlk.adolffront.controls.datetime;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;
import net.mlk.adolffront.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimePicker extends Control {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    private final DateTimePickerSkin pickerSkin;
    private LocalDateTime localDateTime;
    private Font font;

    public DateTimePicker() {
        super.setBackground(Environment.PANELS_BACKGROUND);
        this.pickerSkin = new DateTimePickerSkin(this);
    }

    public DateTimePicker setFont(Font font) {
        this.font = font;
        return this;
    }

    public Font getFont() {
        return this.font;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return this.pickerSkin;
    }
}