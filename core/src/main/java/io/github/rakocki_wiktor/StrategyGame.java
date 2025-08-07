package io.github.rakocki_wiktor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class StrategyGame extends ApplicationAdapter {


    MapGenerator mapGenerator;
    InputHandler inputHandler;
    GameRenderer renderer;
    OrthographicCamera camera;
    ArrayList<Province> provinces;
    public final int MAP_WIDTH = 3000;
    public final int MAP_HEIGHT = 3000;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer = new GameRenderer(camera);
        mapGenerator = new MapGenerator(MAP_WIDTH, MAP_HEIGHT);
        provinces = mapGenerator.generateProvinces();
        inputHandler = new InputHandler(provinces, camera);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        inputHandler.listen();
        camera.update();
        renderer.render(provinces);
    }

    @Override
    public void dispose() {

    }
}
