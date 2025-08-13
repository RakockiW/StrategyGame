package io.github.rakocki_wiktor.model;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;

public class Province {

    float[] vertices;
    int armySize, population;
    boolean hovered;
    boolean selected;
    boolean attacked;
    int type;
    int birthrate;
    Nation nation;
    ArrayList<Province> neighbours;

    public Province(float[] vertices, int type, int armySize, int population) {
        this.vertices = vertices;
        this.armySize = armySize;
        this.population = population;
        this.type = type;
        this.hovered = false;
        this.neighbours = new ArrayList<>();
        this.birthrate = 1;
    }

    public boolean contains(float px, float py) {
        return new Polygon(vertices).contains(px, py);
    }


    public float[] getCenter() {
        float x_sum = 0, y_sum = 0;
        for (int i = 0; i < vertices.length; i=i+2) {
            x_sum += vertices[i];
        }
        for (int i = 1; i < vertices.length; i=i+2) {
            y_sum += vertices[i];
        }

        x_sum /= vertices.length / 2f;
        y_sum /= vertices.length / 2f;

        return new float[]{x_sum, y_sum};

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Color getColor() {
        return (nation != null) ? nation.getColor() : Color.OLIVE;
    }


    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public ArrayList<Province> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Province province) {
        neighbours.add(province);
    }

    public int getPopulation() {
        return population;
    }

    public int getRecruitablePopulation() {
        return Math.min(getPopulation(), nation.getGold());
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getBirthRate() {
        return birthrate;
    }

    public void setBirthrate(int value) {
        this.birthrate = value;
    }
}
