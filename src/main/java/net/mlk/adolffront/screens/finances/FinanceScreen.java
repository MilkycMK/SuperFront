package net.mlk.adolffront.screens.finances;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

import java.time.LocalDate;

public class FinanceScreen extends AbstractMenuElement {
    private final FinanceController controller;
    private VBox infoPanel;
    private Finance finance;

    public FinanceScreen() {
        super("Финансики");
        this.controller = new FinanceController(this);
    }

    @Override
    public void drawScreen() {
        if ((this.finance = this.controller.getFinance()) == null) {
            this.drawFinanceRegisterScreen();
            return;
        }
        this.drawMainScreen();
    }

    public void drawMainScreen() {
        this.drawInfoPanel();
    }

    public void drawInfoPanel() {
        this.infoPanel = new VBox();
        this.infoPanel.setBackground(Environment.CORNER_BACKGROUND);
        this.infoPanel.prefWidthProperty().bind(super.widthProperty().divide(3));
        this.infoPanel.setPadding(new Insets(20));
        Font font = FontUtils.createFont();

        Text text = TextUtils.createText("Баланс: " + this.finance.getRemain(), FontUtils.createFont(FontWeight.BOLD, 20));


        this.infoPanel.getChildren().add(text);

        super.setLeft(this.infoPanel);
    }

    public void drawFinanceRegisterScreen() {
        VBox registerScreen = new VBox();
        registerScreen.setBackground(Environment.PANELS_BACKGROUND);
        registerScreen.maxHeightProperty().bind(super.heightProperty().multiply(0.5));
        registerScreen.maxWidthProperty().bind(super.widthProperty().multiply(0.4));
        registerScreen.spacingProperty().bind(registerScreen.heightProperty().multiply(0.1));
        registerScreen.setAlignment(Pos.CENTER);
        Font font = FontUtils.createFont();

        Text text = TextUtils.createText("Создать учет", FontUtils.createFont(20));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        TextField salary = FieldUtils.createTextField(null, "Зарплата", font);
        FieldUtils.applyOnlyIntegersFilter(salary);
        salary.maxWidthProperty().bind(registerScreen.widthProperty().multiply(0.3));
        DatePicker salaryDate = new DatePicker();
        StyleUtils.setBackground(salaryDate, Environment.BACKGROUND_COLOR);
        salaryDate.maxWidthProperty().bind(registerScreen.widthProperty().multiply(0.4));
        hBox.getChildren().addAll(salary, salaryDate);

        TextField remain = FieldUtils.createTextField(null, "Остаток", font);
        FieldUtils.applyOnlyIntegersFilter(remain);
        remain.maxWidthProperty().bind(registerScreen.widthProperty().multiply(0.5));
        Button submit = ButtonUtils.createButton("Создать", font);
        submit.setOnMouseClicked((e) -> {
            if (salary.getText() == null || salary.getText().isEmpty()) {
                super.setErrorText("Зарплата не может быть пустой.");
                return;
            } else if (salaryDate.getValue() == null) {
                super.setErrorText("Дата зарплаты не может быть пустой.");
                return;
            }
            super.setErrorText("");
            double s = Double.parseDouble(salary.getText());
            LocalDate sd = salaryDate.getValue();
            double r = remain.getText() == null ? 0 : Double.parseDouble(remain.getText());
            this.finance = this.controller.createFinance(s, sd, r);
            if (this.finance != null) {
                this.drawMainScreen();
            }
        });
        registerScreen.getChildren().addAll(text, hBox, remain, submit);
        super.setCenter(registerScreen);
    }

}
