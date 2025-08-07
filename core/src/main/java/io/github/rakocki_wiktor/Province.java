package io.github.rakocki_wiktor;


import com.badlogic.gdx.math.Polygon;

public class Province {

    float[] vertices;
    String armySize;
    boolean hovered;
    int type;

    public Province(float[] vertices) {
        this.vertices = vertices;
        this.armySize = "100";
        this.type = 1;
        this.hovered = false;
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
}
