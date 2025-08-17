package io.github.rakocki_wiktor.logic.actions;

public interface Action {
    boolean canExecute();
    void execute();
    void preTurn();
    ActionType getType();
}
