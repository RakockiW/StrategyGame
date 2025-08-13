package io.github.rakocki_wiktor.model;

import java.util.ArrayList;

public class GameStateData {

    private ArrayList<Province> provinces;
    private ArrayList<Nation> nations;
    private Player player;

    public GameStateData(ArrayList<Province> provinces, ArrayList<Nation> nations, Player player) {
        this.provinces = provinces;
        this.nations = nations;
        this.player = player;
    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public ArrayList<Nation> getNations() {
        return nations;
    }

    public Player getPlayer() {
        return player;
    }
}
