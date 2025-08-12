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
    private UIFactory uiFactory;
    private TextButton attackButton, endTurnButton, recruitButton;
    private Slider selectionSlider;
    private Label selectionSliderLabel;
    private TextArea provinceInfoArea;
    private GameController gameController;

    public UIManager(Stage stage, GameController gameController) {
        this.stage = stage;
        this.gameController = gameController;
        this.table = new Table();

        uiFactory = new UIFactory(stage, table, gameController);
        attackButton = uiFactory.createAttackButton();
        recruitButton = uiFactory.createRecruitButton();
        endTurnButton = uiFactory.createEndTurnButton();
        selectionSliderLabel = uiFactory.createSelectionSliderLabel();
        selectionSlider = uiFactory.createSelectionSlider(selectionSliderLabel);
        provinceInfoArea = uiFactory.createProvinceInfoArea();

        table.top().left();
        table.add(provinceInfoArea).expand().top().left().width(300).height(300).pad(10);
        table.row();
        table.add(attackButton).expand().bottom().left().width(100).height(100).pad(10);
        table.add(recruitButton).expand().bottom().left().width(100).height(100).pad(10);
        table.add(selectionSliderLabel).expand().bottom().width(100).height(100).pad(10);
        table.add(selectionSlider).expand().bottom().height(100).pad(10);
        table.add(endTurnButton).expand().bottom().right().width(100).height(100).pad(10);
    }


    public void showAttackButton() {attackButton.setVisible(true);}

    public void hideAttackButton() {attackButton.setVisible(false);}

    public void showRecruitButton() {recruitButton.setVisible(true);}

    public void hideRecruitButton() {recruitButton.setVisible(false);}

    public void showSelectionSlider() {
        selectionSlider.setVisible(true);
        selectionSliderLabel.setVisible(true);
    }

    public void hideSelectionSlider() {
        selectionSlider.setVisible(false);
        selectionSliderLabel.setVisible(false);
    }

    public int getSelectionSliderValue() {
        return (int) selectionSlider.getValue();
    }

    public void setSelectionSliderMax(int max) {
        selectionSlider.setRange(0f, (float) max);
    }

    public void showProvinceInfoArea() {
        provinceInfoArea.setVisible(true);
    }
    public void hideProvinceInfoArea() {
        provinceInfoArea.setVisible(false);
    }

    public void updateProvinceInfoArea(String owner, int armySize, int population) {
        provinceInfoArea.setText("Province info:\n\n");
        provinceInfoArea.appendText("Nation: " + owner + "\n");
        provinceInfoArea.appendText("Army size: " + armySize + "\n");
        provinceInfoArea.appendText("Population: " + population + "\n");
    }
}
