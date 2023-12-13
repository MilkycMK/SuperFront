package net.mlk.adolffront.screens.group;

import net.mlk.adolffront.http.AdolfServer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GroupController {
    private final GroupScreen screen;

    public GroupController(GroupScreen screen) {
        this.screen = screen;
    }

    public boolean createGroup(Group group) {
        try {
            int id = AdolfServer.createGroup(group);
            if (id == -1) {
                this.screen.setErrorText("Группа уже существует");
                return false;
            }
            group.setId(id);
        } catch (IOException e) {
            this.screen.setErrorText("Ошибка сети.");
            return false;
        }
        return true;
    }

    public boolean createLesson(Group group, Lesson lesson) {
        try {
            int id = AdolfServer.createLesson(group.getId(), lesson);
            if (id == -1) {
                this.screen.setErrorText("Группа уже существует");
                return false;
            }
            lesson.setId(id);
        } catch (IOException e) {
            this.screen.setErrorText("Ошибка сети.");
            return false;
        }
        return true;
    }

    public void createLessonHistory(Group group, Lesson lesson, LessonHistory history) {
        try {
            history.setId(AdolfServer.createLessonHistory(group.getId(), lesson.getId(), history));
        } catch (IOException e) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public Set<Lesson> getLessons(int id) {
        try {
            return id == -1 ? new HashSet<>() : AdolfServer.getLessons(id);
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
            return new HashSet<>();
        }
    }

    public Set<LessonHistory> getLessonHistory(int id, int lId) {
        try {
            return id == -1 ? new HashSet<>() : AdolfServer.getLessonHistory(id, lId);
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
            return new HashSet<>();
        }
    }

    public void deleteGroup(Group group) {
        try {
            AdolfServer.deleteGroup(group.getId());
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public void deleteLesson(Group group, Lesson lesson) {
        try {
            AdolfServer.deleteLesson(group.getId(), lesson.getId());
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

    public void deleteLessonHistory(Group group, Lesson lesson, LessonHistory history) {
        try {
            AdolfServer.deleteLessonHistory(group.getId(), lesson.getId(), history.getId());
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

}
