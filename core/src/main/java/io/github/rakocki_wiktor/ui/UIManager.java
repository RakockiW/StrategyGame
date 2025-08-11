package io.github.rakocki_wiktor.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.rakocki_wiktor.logic.GameController;

public class UIManager implements UIEventListener {

    private Stage stage;
    private Table table;
    private TextButton attackButton, endTurnButton;
    private GameController gameController;

    public UIManager(Stage stage, GameController gameController) {
        this.stage = stage;
        this.gameController = gameController;

        this.table = new Table();
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        table.setFillParent(true);
        stage.addActor(table);

        TextButton endTurnButton = new TextButton("End turn", skin);

        endTurnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.endTurn();
            }
        });
        attackButton = new TextButton("Attack", skin);

        attackButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.onAttackButtonClick();
            }
        });

        attackButton.setVisible(false);

        table.add(attackButton).expand().bottom().left().width(100).height(100);
        table.add(endTurnButton).expand().bottom().right().width(100).height(100);
    }


    public void showAttackButton() {
        attackButton.setVisible(true);
    }
}
