package io.github.rakocki_wiktor.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.rakocki_wiktor.logic.GameController;

public class UIManager implements UIEventListener {

    private Stage stage;
    private Table infoTable, buttonsTable;
    private UIFactory uiFactory;
    private TextButton moveButton, endTurnButton, recruitButton, pickButton;
    private Slider selectionSlider;
    private Label selectionSliderLabel;
    private TextArea provinceInfoArea, nationInfoArea;
    private GameController gameController;

    public UIManager(Stage stage, GameController gameController) {
        this.stage = stage;
        this.gameController = gameController;
        this.infoTable = new Table();
        this.buttonsTable = new Table();
        buttonsTable.setVisible(false);

        uiFactory = new UIFactory(stage, infoTable, buttonsTable, gameController);
        moveButton = uiFactory.createMoveButton();
        recruitButton = uiFactory.createRecruitButton();
        endTurnButton = uiFactory.createEndTurnButton();
        selectionSliderLabel = uiFactory.createSelectionSliderLabel();
        selectionSlider = uiFactory.createSelectionSlider(selectionSliderLabel);
        provinceInfoArea = uiFactory.createProvinceInfoArea();
        nationInfoArea = uiFactory.createNationInfoArea();
        pickButton = uiFactory.createPickButton();

        infoTable.setVisible(false);
        buttonsTable.setVisible(false);


        infoTable.add(provinceInfoArea).expand().top().left().width(300).height(300).pad(10);
        infoTable.add().expandX();
        infoTable.add(nationInfoArea).expand().top().right().width(300).height(300).pad(10);

        buttonsTable.row();
        buttonsTable.add().expandY();
        buttonsTable.add().expandX();
        buttonsTable.add(pickButton).expand().bottom().center().width(500).height(100).pad(10);
        buttonsTable.row();
        buttonsTable.add(moveButton).expand().bottom().left().width(100).height(100).pad(10);
        buttonsTable.add(recruitButton).expand().bottom().left().width(100).height(100).pad(10);
        buttonsTable.add(selectionSliderLabel).expand().bottom().width(100).height(100).pad(10);
        buttonsTable.add(selectionSlider).expand().bottom().height(100).pad(10);
        buttonsTable.add(endTurnButton).expand().bottom().right().width(100).height(100).pad(10);

    }

    public void showMainGameTable() {infoTable.setVisible(true); buttonsTable.setVisible(true);}

    public void showEndTurnButton() {endTurnButton.setVisible(true);}

    public void showMoveButton() {moveButton.setVisible(true);}

    public void hideMoveButton() {moveButton.setVisible(false);}

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

    public void updateNationInfoArea(String name,
                                     int goldAmount,
                                     int provincesAmount,
                                     int actionPoints,
                                     int totalArmySize)
    {
        nationInfoArea.setText("Nation info: \n\n");
        nationInfoArea.appendText("Name: " + name + "\n");
        nationInfoArea.appendText("Gold: " + goldAmount + "\n");
        nationInfoArea.appendText("Controlled provinces: " + provincesAmount + '\n');
        nationInfoArea.appendText("Action points: " + actionPoints + '\n');
        nationInfoArea.appendText("Total army size: " + totalArmySize + '\n');
    }

    public void updateRelationInfo(int relation) {
        nationInfoArea.appendText("Relation: " + relation + '\n');
    }

    public void showPickButton() {
        pickButton.setVisible(true);
    }

    public void hidePickButton() {
        pickButton.setVisible(false);
    }
}
