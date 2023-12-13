package net.mlk.adolffront.screens.group;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        this.groupScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
        Font font = FontUtils.createFont();

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));

        HBox control = new HBox();
        control.setSpacing(20);

        Button newLesson = ButtonUtils.createButton("Добавить пару", font);
        newLesson.setOnMouseClicked((e) -> {
            this.drawLessonCreator();
        });
        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.setOnMouseClicked((e) -> {
            this.drawGroup(group);
        });
        control.getChildren().addAll(newLesson, refresh);

        ScrollPane lessonList = new ScrollPane();
        lessonList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        lessonList.prefHeightProperty().bind(mainContainer.heightProperty());
        VBox lessonBox = new VBox();
        StyleUtils.setBackground(lessonList, Environment.PANELS_COLOR);
        lessonList.setContent(lessonBox);

        DoubleBinding width = lessonList.widthProperty().divide(4);
        Text nameHeader = TextUtils.createText("Название", font);
        nameHeader.wrappingWidthProperty().bind(width.multiply(1.5));
        Text skippedHoursHeader = TextUtils.createText("Часы", font);
        skippedHoursHeader.wrappingWidthProperty().bind(width.divide(2));
        Text hoursHeader = TextUtils.createText("Всего часов", font);
        hoursHeader.wrappingWidthProperty().bind(width);
        GridPane gridPane = new GridPane();
        gridPane.add(nameHeader, 0, 0);
        gridPane.add(skippedHoursHeader, 1, 0);
        gridPane.add(hoursHeader, 2, 0);

        Set<Lesson> lessons = this.controller.getLessons(group.getId());
        for (Lesson lesson : lessons) {
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10));
            hBox.setCursor(Cursor.HAND);
            hBox.hoverProperty().addListener((obs, oldValue, newValue) -> {
                hBox.setBackground(newValue ? Environment.BACKGROUND : Environment.TRANSPARENT_BACKGROUND);
            });
            hBox.setOnMouseClicked((e) -> {
                this.drawLesson(group, lesson);
            });
            hBox.prefWidthProperty().bind(lessonList.widthProperty());
            hBox.prefHeightProperty().bind(lessonList.heightProperty().multiply(0.1));

            GridPane pane = new GridPane();
            pane.setHgap(10);

            Text name = TextUtils.createText(TextUtils.truncateString(lesson.getName(), 30), font);
            Text skippedHours = TextUtils.createText(String.valueOf(lesson.getPassedHours()), font);
            Text hours = TextUtils.createText(String.valueOf(lesson.getHours()), font);

            VBox groupControl = new VBox();
            groupControl.setAlignment(Pos.CENTER);
            Text deleteText = TextUtils.createText("Удалить", FontUtils.createFont(16));
            Text save = TextUtils.createText("Сохранить", FontUtils.createFont(16));

            name.wrappingWidthProperty().bind(width.multiply(1.5));
            skippedHours.wrappingWidthProperty().bind(width.divide(2));
            hours.wrappingWidthProperty().bind(width.divide(2));

            save.setFill(Environment.BUTTONS_COLOR);
            save.setCursor(Cursor.HAND);
            save.setOnMouseClicked((e) -> {
                System.out.println(true);
                e.consume();
            });

            deleteText.setFill(Color.RED);
            deleteText.setCursor(Cursor.HAND);
            deleteText.setOnMouseClicked((e) -> {
                this.controller.deleteLesson(group, lesson);
                lessonBox.getChildren().remove(hBox);
                e.consume();
            });
            groupControl.getChildren().addAll(deleteText);

            pane.add(name, 0, 0);
            pane.add(skippedHours, 1, 0);
            pane.add(hours, 2, 0);
            pane.add(groupControl, 3, 0);

            hBox.getChildren().add(pane);
            lessonBox.getChildren().add(hBox);
        }

        mainContainer.getChildren().addAll(control, gridPane, lessonList);
        super.setCenter(mainContainer);
    }

    public void drawLesson(Group group, Lesson lesson) {
        Font font = FontUtils.createFont();

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));

        HBox control = new HBox();
        control.setSpacing(20);

        Button back = ButtonUtils.createButton("Назад", font);
        back.setOnMouseClicked((e) -> {
            this.drawGroup(group);
        });
        Button newLesson = ButtonUtils.createButton("Добавить пару", font);
        newLesson.setOnMouseClicked((e) -> {
            this.drawHistoryCreator(lesson);
        });
        Button refresh = ButtonUtils.createButton("Обновить список", font);
        refresh.setOnMouseClicked((e) -> {
            this.drawLesson(group, lesson);
        });
        control.getChildren().addAll(back, newLesson, refresh);

        ScrollPane lessonList = new ScrollPane();
        lessonList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        lessonList.prefHeightProperty().bind(mainContainer.heightProperty());
        VBox lessonBox = new VBox();
        StyleUtils.setBackground(lessonList, Environment.PANELS_COLOR);
        lessonList.setContent(lessonBox);

        DoubleBinding width = lessonList.widthProperty().divide(4);
        Text nameHeader = TextUtils.createText("Тема", font);
        nameHeader.wrappingWidthProperty().bind(width.multiply(1.5));
        Text skippedHoursHeader = TextUtils.createText("Часы", font);
        skippedHoursHeader.wrappingWidthProperty().bind(width.divide(2));
        Text hoursHeader = TextUtils.createText("Всего часов", font);
        hoursHeader.wrappingWidthProperty().bind(width);
        GridPane gridPane = new GridPane();
        gridPane.add(nameHeader, 0, 0);
        gridPane.add(skippedHoursHeader, 1, 0);
        gridPane.add(hoursHeader, 2, 0);

        Set<LessonHistory> lessons = this.controller.getLessonHistory(group.getId(), lesson.getId());
        for (LessonHistory history : lessons) {
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10));
            hBox.prefWidthProperty().bind(lessonList.widthProperty());
            hBox.prefHeightProperty().bind(lessonList.heightProperty().multiply(0.1));

            GridPane pane = new GridPane();
            pane.setHgap(10);

            Text name = TextUtils.createText(TextUtils.truncateString(history.getTopic(), 30), font);
            Text skippedHours = TextUtils.createText(String.valueOf(history.getNumber() * 2), font);
            Text hours = TextUtils.createText(String.valueOf(lesson.getHours()), font);

            VBox groupControl = new VBox();
            groupControl.setAlignment(Pos.CENTER);
            Text deleteText = TextUtils.createText("Удалить", FontUtils.createFont(16));

            name.wrappingWidthProperty().bind(width.multiply(1.5));
            skippedHours.wrappingWidthProperty().bind(width.divide(2));
            hours.wrappingWidthProperty().bind(width.divide(2));

            deleteText.setFill(Color.RED);
            deleteText.setCursor(Cursor.HAND);
            deleteText.setOnMouseClicked((e) -> {
                this.controller.deleteLessonHistory(group, lesson, history);
                this.drawLesson(group, lesson);
                e.consume();
            });
            groupControl.getChildren().add(deleteText);

            pane.add(name, 0, 0);
            pane.add(skippedHours, 1, 0);
            pane.add(hours, 2, 0);
            pane.add(groupControl, 3, 0);

            hBox.getChildren().add(pane);
            lessonBox.getChildren().add(hBox);
        }

        mainContainer.getChildren().addAll(control, gridPane, lessonList);
        super.setCenter(mainContainer);
    }

    public void drawLessonCreator() {
        VBox creationScreen = new VBox();
        creationScreen.setBackground(Environment.PANELS_BACKGROUND);
        creationScreen.maxHeightProperty().bind(super.heightProperty().multiply(0.5));
        creationScreen.maxWidthProperty().bind(super.widthProperty().multiply(0.4));
        creationScreen.spacingProperty().bind(creationScreen.heightProperty().multiply(0.05));
        creationScreen.setAlignment(Pos.CENTER);
        Font font = FontUtils.createFont();

        TextField lessonName = FieldUtils.createTextField(null, "Название", font);
        lessonName.maxWidthProperty().bind(creationScreen.widthProperty().multiply(0.5));
        FieldUtils.setMaxTextLength(lessonName, 255);
        TextField hours = FieldUtils.createTextField(null, "Всего часов", font);
        hours.maxWidthProperty().bind(creationScreen.widthProperty().multiply(0.5));
        FieldUtils.applyOnlyIntegersFilter(hours);

        Button submit = ButtonUtils.createButton("Создать", font);
        submit.setOnMouseClicked((e) -> {
            if (this.currentElement == null || this.currentElement.getId() != -1) {
                if (lessonName.getText() == null) {
                    super.setErrorText("Название пары не может быть пустым");
                    return;
                } else if (hours.getText() == null) {
                    super.setErrorText("Кол-во часов не может быть пустым");
                    return;
                } else if (Integer.parseInt(hours.getText()) % 2 != 0) {
                    super.setErrorText("Неверный формат часов (Только четные)");
                    return;
                }
                Lesson lesson = new Lesson(lessonName.getText(), Integer.parseInt(hours.getText()));
                if (this.controller.createLesson(this.currentElement, lesson)) {
                    this.drawGroup(this.currentElement);
                }
            }
        });
        Button back = ButtonUtils.createButton("Вернуться", font);
        back.setOnMouseClicked((e) -> {
            this.drawGroup(this.currentElement);
        });

        creationScreen.getChildren().addAll(lessonName, hours, submit, back);
        super.setCenter(creationScreen);
    }

    public void drawHistoryCreator(Lesson lesson) {
        VBox creationScreen = new VBox();
        creationScreen.setBackground(Environment.PANELS_BACKGROUND);
        creationScreen.maxHeightProperty().bind(super.heightProperty().multiply(0.5));
        creationScreen.maxWidthProperty().bind(super.widthProperty().multiply(0.4));
        creationScreen.spacingProperty().bind(creationScreen.heightProperty().multiply(0.05));
        creationScreen.setAlignment(Pos.CENTER);
        Font font = FontUtils.createFont();

        TextField lessonName = FieldUtils.createTextField(null, "Тема", font);
        lessonName.maxWidthProperty().bind(creationScreen.widthProperty().multiply(0.5));
        FieldUtils.setMaxTextLength(lessonName, 255);
        DatePicker picker = new DatePicker();
        StyleUtils.setBackground(picker, Environment.BACKGROUND_COLOR);

        Button submit = ButtonUtils.createButton("Создать", font);
        submit.setOnMouseClicked((e) -> {
            if (this.currentElement == null || this.currentElement.getId() != -1) {
                if (picker.getValue() == null) {
                    super.setErrorText("Дата не может быть пустой");
                    return;
                }
                LessonHistory history = new LessonHistory(lessonName.getText(), picker.getValue());
                this.controller.createLessonHistory(this.currentElement, lesson, history);
                this.drawLesson(this.currentElement, lesson);
            }
        });
        Button back = ButtonUtils.createButton("Вернуться", font);
        back.setOnMouseClicked((e) -> {
            this.drawLesson(this.currentElement, lesson);
        });

        creationScreen.getChildren().addAll(lessonName, picker, submit, back);
        super.setCenter(creationScreen);
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
                    return;
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
        vBox.minWidthProperty().bind(this.groupScroll.widthProperty());
        vBox.setPadding(new Insets(20));
        Text name = TextUtils.createText(TextUtils.truncateString(group.getName(), 16), FontUtils.createFont(FontWeight.BOLD, 15));
        Text delete = TextUtils.createText("Удалить", FontUtils.createFont(), Color.RED);
        delete.setOnMouseClicked((e) -> {
            this.controller.deleteGroup(group);
            this.updateGroups();
            if (this.currentElement == group) {
                this.openGroup(null);
            }
            this.drawGroupList();
            e.consume();
        });
        vBox.getChildren().addAll(name, delete);


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
        if (group == null) {
            this.setCenter(null);
            return;
        }
        this.drawGroup(group);
    }

}
