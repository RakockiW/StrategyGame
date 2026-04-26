package io.github.rakocki_wiktor.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;

public class GameRenderer {

    private ProvinceRenderer provinceRenderer;
    private ActionsRenderer actionsRenderer;
    private UIRenderer uiRenderer;
    private ShapeRenderer shape;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;


    public GameRenderer(OrthographicCamera camera, Stage stage) {
        this.shape = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        provinceRenderer = new ProvinceRenderer(shape, batch, font, camera);
        actionsRenderer = new ActionsRenderer(shape, batch, font, camera);
        uiRenderer = new UIRenderer(stage);
    }

    public void render(GameStateData state) {
        provinceRenderer.render(state.getProvinces());
        actionsRenderer.render(state);
        uiRenderer.render();
    }
}
