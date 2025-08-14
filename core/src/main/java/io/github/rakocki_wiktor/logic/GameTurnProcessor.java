package io.github.rakocki_wiktor.logic;

import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;

public class GameTurnProcessor {

    private ArrayList<Nation> nations;
    private ArrayList<Province> provinces;

    public GameTurnProcessor(GameStateData gameStateData) {
        this.nations = gameStateData.getNations();
        this.provinces = gameStateData.getProvinces();
    }

    public void processTurn() {
        addNationProfits();
        growPopulation();
    }

    public void addNationProfits() {
        for (Province province : provinces) {
            int profit = province.getPopulation() / 2;
            province.getNation().addGold(profit);
        }
    }

    public void growPopulation() {
        for (Province province : provinces) {
            int growth = (province.getPopulation() * province.getBirthRate()) / 100;
            province.setPopulation(province.getPopulation() + growth + 1);
        }
    }
}
