package io.github.rakocki_wiktor.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.rakocki_wiktor.logic.GameController;

public class UIFactory {

    private Table table;
    private Skin skin;
    private GameController gameController;

    public UIFactory(Stage stage, Table table, GameController gameController) {
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.gameController = gameController;
        this.table = table;

        table.setFillParent(true);
        stage.addActor(table);
    }

    public TextButton createAttackButton() {
        TextButton attackButton = new TextButton("Attack", skin);

        attackButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.onAttackButtonClick();
            }
        });

        attackButton.setVisible(false);

        return attackButton;
    }

    public TextButton createRecruitButton() {
        TextButton recruitButton = new TextButton("Recruit", skin);

        recruitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.onRecruitButtonClick();
            }
        });

        recruitButton.setVisible(false);

        return recruitButton;
    }

    public TextButton createEndTurnButton() {
        TextButton endTurnButton = new TextButton("End turn", skin);

        endTurnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.endTurn();
            }
        });

        return endTurnButton;
    }

    public Label createSelectionSliderLabel() {
        Label selectionSliderLabel = new Label("0", skin);
        selectionSliderLabel.setVisible(false);

        return selectionSliderLabel;
    }

    public Slider createSelectionSlider(Label selectionSliderLabel) {
        Slider selectionSlider = new Slider(0f, 100f, 1f, false, skin);
        selectionSlider.setVisible(false);

        selectionSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = selectionSlider.getValue();
                selectionSliderLabel.setText("Troops: " + (int) value);
            }
        });

        return selectionSlider;
    }

    public TextArea createProvinceInfoArea() {
        TextArea provinceInfoArea = new TextArea("Province Info\n", skin);
        provinceInfoArea.setVisible(false);

        return provinceInfoArea;
    }

}
