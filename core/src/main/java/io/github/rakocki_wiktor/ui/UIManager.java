package io.github.rakocki_wiktor.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.rakocki_wiktor.logic.GameController;

public class UIManager implements UIEventListener {

    private Stage stage;
    private Table table;
    private TextButton attackButton, endTurnButton;
    private Slider armySlider;
    private Label armySliderLabel;
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
        armySliderLabel = new Label("0", skin);
        armySlider = new Slider(0f, 100f, 1f, false, skin);

        armySliderLabel.setVisible(false);
        armySlider.setVisible(false);

        armySlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = armySlider.getValue();
                armySliderLabel.setText("Troops: " + (int) value);
            }
        });

        table.add(attackButton).expand().bottom().left().width(100).height(100);
        table.add(armySliderLabel).expand().bottom().width(100).height(100);
        table.add(armySlider).expand().bottom().height(100);
        table.add(endTurnButton).expand().bottom().right().width(100).height(100);
    }


    public void showAttackButton() {
        attackButton.setVisible(true);
    }

    public void hideAttackButton() {attackButton.setVisible(false);}

    public void showArmySelection() {
        armySlider.setVisible(true);
        armySliderLabel.setVisible(true);
    }

    public void hideArmySelection() {
        armySlider.setVisible(false);
        armySliderLabel.setVisible(false);
    }

    public int getArmySliderValue() {
        return (int) armySlider.getValue();
    }

    public void setArmySliderMax(int max) {
        armySlider.setRange(0f, (float) max);
    }
}
