package io.github.rakocki_wiktor.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UIRenderer {
    private Stage stage;
    private TextButton attackButton;

    public UIRenderer(Stage stage, Table table) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        stage.addActor(table);

        TextButton endTurnButton = new TextButton("End turn", skin);
        attackButton = new TextButton("Attack", skin);
        table.add(attackButton).expand().bottom().left().width(100).height(100);
        attackButton.setVisible(false);
        table.add(endTurnButton).expand().bottom().right().width(100).height(100);

    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void showAttackButton() {
        attackButton.setVisible(true);
    }
}
