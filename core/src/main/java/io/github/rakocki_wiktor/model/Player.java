package io.github.rakocki_wiktor.model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class Player {
    ArrayList<Province> ownedProvinces;
    Color color;

    public Player() {
        ownedProvinces = new ArrayList<>();
        color = Color.OLIVE;
    }

    public Color getColor() {
        return color;
    }
}
