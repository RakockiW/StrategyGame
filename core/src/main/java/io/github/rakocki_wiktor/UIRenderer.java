package io.github.rakocki_wiktor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIRenderer {
    private Stage stage;

    public UIRenderer(Stage stage, Table table) {
        this.stage = stage;

        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        stage.addActor(table);
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
