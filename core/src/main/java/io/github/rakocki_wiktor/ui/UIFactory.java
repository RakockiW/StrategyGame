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

    private Skin skin;
    private GameController gameController;

    public UIFactory(Stage stage, Table infoTable, Table buttonsTable, GameController gameController) {
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.gameController = gameController;

        infoTable.setFillParent(true);
        buttonsTable.setFillParent(true);
        stage.addActor(infoTable);
        stage.addActor(buttonsTable);
    }

    public TextButton createMoveButton() {
        TextButton moveButton = new TextButton("Move", skin);

        moveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.onMoveButtonClick();
            }
        });

        moveButton.setVisible(false);

        return moveButton;
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

        endTurnButton.setVisible(false);
        return endTurnButton;
    }

    public Label createSelectionSliderLabel() {
        Label selectionSliderLabel = new Label("Troops: ", skin);
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

    public TextArea createNationInfoArea() {
        return new TextArea("Nation Info\n", skin);
    }

    public TextButton createPickButton() {
        TextButton pickButton = new TextButton("Pick a nation", skin);
        pickButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gameController.onPickButtonClick();
            }
        });

        pickButton.setVisible(true);

        return pickButton;
    }

}
