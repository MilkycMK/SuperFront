package net.mlk.adolffront.screens.group;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import net.mlk.adolffront.Environment;
import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.screens.menu.AbstractMenuElement;
import net.mlk.adolffront.utils.StyleUtils;
import net.mlk.adolffront.utils.elements.ButtonUtils;
import net.mlk.adolffront.utils.elements.FieldUtils;
import net.mlk.adolffront.utils.elements.FontUtils;
import net.mlk.adolffront.utils.elements.TextUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GroupScreen extends AbstractMenuElement {
    private final GroupController controller;
    private ScrollPane groupScroll = new ScrollPane();
    private Set<Group> groups = new HashSet<>();
    private VBox currentBox;
    private Group currentElement;
    private VBox leftPanel;

    public GroupScreen() {
        super("Группы");
        this.controller = new GroupController(this);
        super.getErrorText().translateXProperty().bind(super.widthProperty().multiply(0.5));
    }

    @Override
    public void drawScreen() {
        if (this.groups.isEmpty()) {
            this.updateGroups();
        }
        this.drawLeftMenu();
        this.drawGroupList();
        this.leftPanel.getChildren().add(this.groupScroll);
    }

    public void drawGroup(Group group) {

    }

    public void drawLeftMenu() {
        this.leftPanel = new VBox();
        this.leftPanel.setBackground(Environment.CORNER_BACKGROUND);
        this.leftPanel.prefWidthProperty().bind(super.widthProperty().divide(3));
        Font font = FontUtils.createFont();

        VBox buttons = new VBox();
        buttons.spacingProperty().bind(this.leftPanel.heightProperty().multiply(0.04));
        buttons.setPadding(new Insets(20));
        buttons.setAlignment(Pos.CENTER);

        Button create = ButtonUtils.createButton("Создать группу", font);
        create.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        create.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        create.setOnMouseClicked((e) -> this.drawCreationScreen());

        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.prefWidthProperty().bind(buttons.widthProperty().multiply(0.7));
        refresh.minHeightProperty().bind(buttons.heightProperty().multiply(0.08));
        refresh.setOnMouseClicked((e) -> {
            this.updateGroups();
            this.drawGroupList();
        });

        buttons.getChildren().addAll(create, refresh);
        this.leftPanel.getChildren().add(buttons);
        super.setLeft(this.leftPanel);
    }

    public void drawGroupList() {
        StyleUtils.setBackground(this.groupScroll, Environment.BACKGROUND_COLOR);
        this.groupScroll.prefHeightProperty().bind(this.leftPanel.heightProperty().multiply(0.77));
        VBox todos = new VBox();
        this.groupScroll.setContent(todos);

        for (Group element : this.groups) {
            this.addGroup(element, this.currentElement != null && this.currentElement.getId() == element.getId());
        }
    }

    public void drawCreationScreen() {
        VBox creationScreen = new VBox();
        creationScreen.setBackground(Environment.PANELS_BACKGROUND);
        creationScreen.maxHeightProperty().bind(super.heightProperty().multiply(0.5));
        creationScreen.maxWidthProperty().bind(super.widthProperty().multiply(0.4));
        creationScreen.spacingProperty().bind(creationScreen.heightProperty().multiply(0.1));
        creationScreen.setAlignment(Pos.CENTER);
        Font font = FontUtils.createFont();

        TextField groupName = FieldUtils.createTextField(null, "Имя группы", font);
        groupName.maxWidthProperty().bind(creationScreen.widthProperty().multiply(0.5));
        FieldUtils.setMaxTextLength(groupName, 32);

        Button submit = ButtonUtils.createButton("Создать", font);
        submit.setOnMouseClicked((e) -> {
            if (this.currentElement == null || this.currentElement.getId() != -1) {
                if (groupName.getText() == null) {
                    super.setErrorText("Имя группы не может быть пустым");
                }
                Group group = new Group(groupName.getText());
                if (this.controller.createGroup(group)) {
                    this.addGroup(group, true);
                    this.openGroup(group);
                }
            }
        });

        creationScreen.getChildren().addAll(groupName, submit);
        super.setCenter(creationScreen);
    }

    public void updateGroups() {
        try {
            this.groups = AdolfServer.getGroups();
        } catch (IOException ex) {
            super.setErrorText("Ошибка сети.");
        }
    }

    private void addGroup(Group group, boolean current) {
        VBox vBox = new VBox();
        vBox.setCursor(Cursor.HAND);
        vBox.minHeightProperty().bind(this.groupScroll.heightProperty().multiply(0.1));
        vBox.minWidthProperty().bind(this.groupScroll.widthProperty().multiply(0.93));
        vBox.setPadding(new Insets(20));
        Text name = TextUtils.createText(group.getName(), FontUtils.createFont(FontWeight.BOLD, 15));
        vBox.getChildren().add(name);

        if (current) {
            if (this.currentBox != null) {
                this.currentBox.setBackground(Environment.BACKGROUND);
            }
            this.currentBox = vBox;
            this.currentBox.setBackground(Environment.TODO_ACTIVE);
        }
        vBox.setOnMouseClicked((e) -> {
            if (this.currentBox != null) {
                this.currentBox.setBackground(Environment.BACKGROUND);
            }
            this.currentBox = vBox;
            this.currentBox.setBackground(Environment.TODO_ACTIVE);
            this.openGroup(group);
        });

        ((Pane) this.groupScroll.getContent()).getChildren().add(vBox);
    }

    public void openGroup(Group group) {
        this.currentElement = group;
        this.drawGroup(group);
    }

}
