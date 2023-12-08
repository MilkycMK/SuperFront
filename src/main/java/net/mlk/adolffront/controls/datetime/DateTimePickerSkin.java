package net.mlk.adolffront.controls.datetime;

import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import net.mlk.adolffront.utils.StyleUtils;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;

public class DateTimePickerSkin extends SkinBase<DateTimePicker> {
    private final DateTimePicker picker;
    private final TextField dateTimeField;

    public DateTimePickerSkin(DateTimePicker picker) {
        super(picker);
        this.picker = picker;

        LocalDateTime time = LocalDateTime.now();
        this.dateTimeField = StyleUtils.createTextField(time.format(DateTimePicker.formatter), "",
                this.picker.getFont(), 0, 0);
        this.dateTimeField.setTextFormatter(this.createFormatter());
        this.drawDateTimeField();
    }

    private TextFormatter<String> createFormatter() {
        String pattern = "yyyy.MM.dd HH:mm:ss";

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String oldText = change.getControlText();
            String newText = change.getControlNewText();
            if (!change.isContentChange()) {
                return change;
            }
            System.out.println(newText.length() - 1);
            int pos = change.getCaretPosition() - 1;

            if (change.isDeleted()) {

                System.out.println(newText.charAt(pos) + newText + pos);
                char current = oldText.charAt(pos);
                if (current == ':') {
                    change.setCaretPosition(pos);
                    change.setRange(pos, pos);
                    return null;
                }
            } else if (change.isAdded()) {
                return null;
            } else if (change.isReplaced()) {
                return null;
            }


            return change;
        };

        return new TextFormatter<>(filter);
    }

    private void drawDateTimeField() {
        this.dateTimeField.setPrefSize(this.picker.getPrefWidth(), this.picker.getMinHeight());
        this.dateTimeField.setMinSize(this.picker.getMinWidth(), this.picker.getMinHeight());
        this.dateTimeField.setMaxSize(this.picker.getMaxWidth(), this.picker.getMaxHeight());
        this.dateTimeField.setBackground(this.picker.getBackground());

        super.getChildren().addAll(this.dateTimeField);
    }

    public TextField getDateTimeField() {
        return this.dateTimeField;
    }
}
