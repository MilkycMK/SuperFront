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

}
