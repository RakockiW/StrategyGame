package io.github.rakocki_wiktor.model;

import io.github.rakocki_wiktor.logic.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class GameStateData {

    private ArrayList<Province> provinces;
    private ArrayList<Nation> nations;
    private List<Action> plannedActions;
    private Player player;

    public GameStateData(ArrayList<Province> provinces, ArrayList<Nation> nations, Player player) {
        this.provinces = provinces;
        this.nations = nations;
        this.player = player;
        this.plannedActions = new ArrayList<>();
    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public ArrayList<Nation> getNations() {
        return nations;
    }

    public void setActions(List<Action> actions) {
        plannedActions = actions;
    }

    public List<Action> getActions() {
        return plannedActions;
    }

    public Player getPlayer() {
        return player;
    }
}
