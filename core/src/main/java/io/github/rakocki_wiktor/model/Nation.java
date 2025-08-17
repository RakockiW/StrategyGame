package io.github.rakocki_wiktor.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;

import java.util.*;

public class Nation {
    ArrayList<Province> ownedProvinces;
    Color color;
    String name;
    int gold;
    int actionPoints;
    final UUID id;

    public Nation() {
        ownedProvinces = new ArrayList<>();
        color = Color.OLIVE;
        id = UUID.randomUUID();
    }

    public Color getColor() {
        return color;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void addActionPoints(int amount) {
        actionPoints += amount;
    }

    public void removeGold(int amount) {
        gold -= amount;
    }

    public void removeActionPoints(int amount) {
        actionPoints -= amount;
    }

    public int getGold() {
        return gold;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void addProvince(Province province) {
        ownedProvinces.add(province);
    }

    public ArrayList<Province> getOwnedProvinces() {
        return ownedProvinces;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvincesAmount() {
        return ownedProvinces.size();
    }

    public void setGold(int amount) {
        this.gold = amount;
    }

    public int getTotalArmySize() {
        int totalSize = 0;

        for (Province province : ownedProvinces) {
            totalSize += province.getArmySize();
        }

        return totalSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Nation nation)) return false;
        return Objects.equals(this.id, nation.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}

