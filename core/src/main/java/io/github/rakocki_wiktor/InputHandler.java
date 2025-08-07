package io.github.rakocki_wiktor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class InputHandler {

    ArrayList<Province> provinces;
    OrthographicCamera camera;

    public InputHandler(ArrayList<Province> provinces, OrthographicCamera camera) {
        this.provinces = provinces;
        this.camera = camera;
    }
    public void listen() {
        handleMouseHover();
        handleCameraMovement();
    }

    private void handleMouseHover() {
        Vector3 worldCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        float mouseX = worldCoords.x;
        float mouseY = worldCoords.y;

        for (Province province : provinces) {
            province.hovered = province.contains(mouseX, mouseY);
        }
    }

    private void handleCameraMovement() {
        float speed = 300 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.position.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.position.y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.position.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.position.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) camera.zoom += 0.02f;
        if (Gdx.input.isKeyPressed(Input.Keys.E)) camera.zoom -= 0.02f;




    }

}
