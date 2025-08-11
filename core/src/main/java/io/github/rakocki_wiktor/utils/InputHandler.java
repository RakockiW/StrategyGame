package io.github.rakocki_wiktor.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import io.github.rakocki_wiktor.logic.GameController;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;

public class InputHandler extends InputAdapter {

    ArrayList<Province> provinces;
    OrthographicCamera camera;
    GameController gameController;

    public InputHandler(ArrayList<Province> provinces, OrthographicCamera camera, GameController gameController) {
        this.provinces = provinces;
        this.camera = camera;
        this.gameController = gameController;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT) return false;
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        float mouseX = worldCoords.x;
        float mouseY = worldCoords.y;

        for (Province province : provinces) {
            if (province.contains(mouseX, mouseY)) {
                gameController.onProvinceClick(province);
                return true;
            }
        }

        return false;

    }

    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        float mouseX = worldCoords.x;
        float mouseY = worldCoords.y;

        for (Province province : provinces) {
            province.setHovered(province.contains(mouseX, mouseY));
        }

        return false;
    }


    public void update() {
        float speed = 300 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.position.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.position.y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.position.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.position.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) camera.zoom += 0.02f;
        if (Gdx.input.isKeyPressed(Input.Keys.E)) camera.zoom -= 0.02f;
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) camera.rotate(1f);
        if (Gdx.input.isKeyPressed(Input.Keys.X)) camera.rotate(-1f);
    }

}
