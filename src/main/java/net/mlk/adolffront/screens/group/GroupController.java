package net.mlk.adolffront.screens.group;

import net.mlk.adolffront.http.AdolfServer;

import java.io.IOException;

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

}
