package io.github.rakocki_wiktor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;

public class GameRenderer {

    private ProvinceRenderer provinceRenderer;
    private UIRenderer uiRenderer;
    private ShapeRenderer shape;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Table table;


    public GameRenderer(OrthographicCamera camera) {
        this.shape = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.stage = new Stage();
        this.table = new Table();
        provinceRenderer = new ProvinceRenderer(shape, batch, font, camera);
        uiRenderer = new UIRenderer(stage, table);
    }

    public void render(ArrayList<Province> provinces) {
        provinceRenderer.render(provinces);
        uiRenderer.render();
    }
}
