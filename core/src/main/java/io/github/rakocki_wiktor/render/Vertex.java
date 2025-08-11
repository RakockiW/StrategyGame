package io.github.rakocki_wiktor.render;

public class Vertex {
    float x, y;
    float offsetX = 0, offsetY = 0;

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return  x + offsetX; };
    public float getY() { return y + offsetY; };

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }
}
