package io.github.rakocki_wiktor;

public class Vertex {
    float x, y;
    float offsetX = 0, offsetY = 0;

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return  x + offsetX; };
    public float getY() { return y + offsetY; };
}
