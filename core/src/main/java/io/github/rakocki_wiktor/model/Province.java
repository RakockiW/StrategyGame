package io.github.rakocki_wiktor.model;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;

public class Province {

    float[] vertices;
    int armySize;
    boolean hovered;
    boolean selected;
    int type;
    Player owner;
    Color color;

    public Province(float[] vertices, int type, int armySize, Player owner) {
        this.vertices = vertices;
        this.armySize = armySize;
        this.type = type;
        this.hovered = false;
        this.owner = owner;
        this.color = owner.getColor();
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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
