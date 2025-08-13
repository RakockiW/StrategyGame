package io.github.rakocki_wiktor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.rakocki_wiktor.logic.GameController;
import io.github.rakocki_wiktor.map.MapPopulator;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Player;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.render.GameRenderer;
import io.github.rakocki_wiktor.ui.UIManager;
import io.github.rakocki_wiktor.utils.InputHandler;
import io.github.rakocki_wiktor.map.MapGenerator;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class StrategyGame extends ApplicationAdapter {


    MapGenerator mapGenerator;
    MapPopulator mapPopulator;
    InputHandler inputHandler;
    GameRenderer renderer;
    UIManager uiManager;
    OrthographicCamera camera;
    ArrayList<Province> provinces;
    ArrayList<Nation> nations;
    public final int MAP_WIDTH = 5000;
    public final int MAP_HEIGHT = 3000;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Stage stage = new Stage(new ScreenViewport());
        renderer = new GameRenderer(camera, stage);

        provinces = MapGenerator.generateProvinces(MAP_HEIGHT, MAP_WIDTH);
        nations = MapPopulator.populate(provinces);
        Player player = new Player();
        GameStateData gameStateData = new GameStateData(provinces, nations, player);

        GameController gameController = new GameController(gameStateData);
        inputHandler = new InputHandler(provinces, camera, gameController);
        uiManager = new UIManager(stage, gameController);
        gameController.setUiEventListener(uiManager);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(inputHandler);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        inputHandler.update();
        camera.update();
        renderer.render(provinces);
    }

    @Override
    public void dispose() {

    }
}
