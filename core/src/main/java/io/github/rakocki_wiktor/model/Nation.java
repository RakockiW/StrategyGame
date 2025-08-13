package io.github.rakocki_wiktor.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;

import java.util.ArrayList;
import java.util.Collection;

public class Nation {
    ArrayList<Province> ownedProvinces;
    Color color;
    String name;
    int gold;
    int actionPoints;

    public Nation() {
        ownedProvinces = new ArrayList<>();
        color = Color.OLIVE;
    }

    public Color getColor() {
        return color;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void addActionPoints(int amount) {
        actionPoints -= amount;
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
}

