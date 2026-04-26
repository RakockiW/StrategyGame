package io.github.rakocki_wiktor.logic.actions;

import java.util.ArrayList;
import java.util.List;

public class ActionsManager {
    public ArrayList<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public void removeAllActions() {
        actions.clear();
    }

    public void executeActions() {
        for (Action action : actions) {
            action.execute();
        }
    }

    public List<Action> getActions() {
        return actions;
    }

}
