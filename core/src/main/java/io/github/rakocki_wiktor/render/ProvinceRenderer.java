package io.github.rakocki_wiktor.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;

public class ProvinceRenderer {
    private final ShapeRenderer shape;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();


    public ProvinceRenderer(ShapeRenderer shape, SpriteBatch batch, BitmapFont font, OrthographicCamera camera) {
        this.shape = shape;
        this.batch = batch;
        this.font = font;
        this.camera = camera;
    }

    public void render(ArrayList<Province> provinces) {
        shape.setProjectionMatrix(camera.combined);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Province province : provinces) {

            if (province.isHovered()) {
                shape.setColor(Color.GREEN);
            } else {
                if (province.getType() == 1) shape.setColor(province.getOwner().getColor());
                else shape.setColor(Color.BLUE);
            }

            if (province.isSelected()) {
                shape.setColor(Color.BLUE);
            }

            if (province.isAttacked()) {
                shape.setColor(Color.RED);
            }

            float[] vertices = province.getVertices();
            ShortArray indices = triangulator.computeTriangles(vertices);

            for (int i = 0; i < indices.size; i += 3) {
                int i1 = indices.get(i) * 2;
                int i2 = indices.get(i + 1) * 2;
                int i3 = indices.get(i + 2) * 2;

                shape.triangle(
                    vertices[i1], vertices[i1+1],
                    vertices[i2], vertices[i2+1],
                    vertices[i3], vertices[i3+1]
                );
             }
        }

        shape.end();


        shape.begin(ShapeRenderer.ShapeType.Line);
        for (Province province : provinces) {
            if (!province.isHovered()) {
                shape.setColor(Color.BLACK);
                shape.polygon(province.getVertices());
            }
        }
        for (Province province : provinces) {
            if (province.isHovered()) {
                shape.setColor(Color.WHITE);
                shape.polygon(province.getVertices());
            }
        }
        shape.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Province province : provinces) {
            if (province.isHovered()) {
                float[] center = province.getCenter();
                font.draw(batch, String.valueOf(province.getArmySize()), center[0], center[1]);
            }
        }
        batch.end();
    }

    public void dispose() {
        shape.dispose();
        batch.dispose();
        font.dispose();
    }
}
