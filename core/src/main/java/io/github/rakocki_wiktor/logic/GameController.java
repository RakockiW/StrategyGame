package io.github.rakocki_wiktor.logic;

import io.github.rakocki_wiktor.logic.actions.ActionsManager;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.logic.actions.MoveUnitsAction;
import io.github.rakocki_wiktor.logic.actions.RecruitAction;
import io.github.rakocki_wiktor.logic.ai.AIController;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Player;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.ui.UIEventListener;

public class GameController {

    private final Player player;
    private ActionsManager actionsManager;
    private GameStateData gameStateData;
    private Province selectedProvince;
    private UIEventListener uiEventListener;
    private GameState state = GameState.PICKING_NATION;
    private GameTurnProcessor turnProcessor;
    private AIController aiController;

    public GameController(GameStateData gameStateData) {
        actionsManager = new ActionsManager();
        this.gameStateData = gameStateData;
        this.player = gameStateData.getPlayer();
        this.turnProcessor = new GameTurnProcessor(gameStateData);
        this.aiController = new AIController(gameStateData, actionsManager);
    }

    public void setUiEventListener(UIEventListener uiEventListener) {
        this.uiEventListener = uiEventListener;
    }

    public void onMoveButtonClick() {
        state = GameState.MOVING;

        uiEventListener.showSelectionSlider();
        if (selectedProvince != null) {
            uiEventListener.setSelectionSliderMax(selectedProvince.getArmySize());
        }

        uiEventListener.hideMoveButton();
        uiEventListener.hideRecruitButton();
    }

    public void onRecruitButtonClick() {
        switch (state) {
            case IDLE -> {
                uiEventListener.showSelectionSlider();
                uiEventListener.setSelectionSliderMax(selectedProvince.getRecruitablePopulation());
                state = GameState.RECRUITING;
            }

            case RECRUITING -> {
                int recruitedPopulation = uiEventListener.getSelectionSliderValue();
                if (selectedProvince != null) {
                    uiEventListener.setSelectionSliderMax(selectedProvince.getRecruitablePopulation());
                    selectedProvince.setPopulation(selectedProvince.getPopulation() - recruitedPopulation);
                }
                RecruitAction action = new RecruitAction(selectedProvince, recruitedPopulation);
                action.preTurn();
                actionsManager.addAction(action);
                uiEventListener.hideSelectionSlider();
                state = GameState.IDLE;
            }
        }

    }

    public void onProvinceClick(Province clickedProvince) {

        if (selectedProvince != null) {
            selectedProvince.setSelected(false);
        }

        switch (state) {
            case MOVING -> {
                if (selectedProvince.getNeighbours().contains(clickedProvince)) {
                    int armySize = uiEventListener.getSelectionSliderValue();

                    if (clickedProvince.getNation() == player.getNation()) {
                        MoveUnitsAction action = new MoveUnitsAction(selectedProvince, clickedProvince, armySize);
                        action.preTurn();
                        actionsManager.addAction(action);
                    } else {
                        AttackAction action = new AttackAction(selectedProvince, clickedProvince, armySize);
                        action.preTurn();
                        actionsManager.addAction(action);
                        clickedProvince.setAttacked(true);
                    }
                    uiEventListener.hideSelectionSlider();
                    state = GameState.IDLE;
                }
            }

            case IDLE -> {
                selectedProvince = clickedProvince;
                selectedProvince.setSelected(true);
                uiEventListener.hideSelectionSlider();
                uiEventListener.showProvinceInfoArea();
                updateInfoAreas();
                if (selectedProvince.getNation() == player.getNation()) {
                    uiEventListener.showMoveButton();
                    uiEventListener.showRecruitButton();
                } else {
                    uiEventListener.hideMoveButton();
                    uiEventListener.hideRecruitButton();
                }
            }

            case PICKING_NATION -> {
                selectedProvince = clickedProvince;
                selectedProvince.setSelected(true);
                uiEventListener.showPickButton();
                uiEventListener.showMainGameTable();
                uiEventListener.showProvinceInfoArea();
            }
        }

        updateInfoAreas();

    }

    public void onPickButtonClick() {

        if (selectedProvince != null) {
            player.setNation(selectedProvince.getNation());
            uiEventListener.showEndTurnButton();
            uiEventListener.hidePickButton();
            state = GameState.IDLE;
        }

    }

    private void updateInfoAreas() {
        uiEventListener.updateProvinceInfoArea(
            selectedProvince.getNation().getName(),
            selectedProvince.getArmySize(),
            selectedProvince.getPopulation());
        uiEventListener.updateNationInfoArea(
            selectedProvince.getNation().getName(),
            selectedProvince.getNation().getGold(),
            selectedProvince.getNation().getProvincesAmount(),
            selectedProvince.getNation().getActionPoints(),
            selectedProvince.getNation().getTotalArmySize()
        );

        if (player.getNation() != null && selectedProvince.getNation() != player.getNation()) uiEventListener.updateRelationInfo(player.getNation().getRelation(selectedProvince.getNation()));
    }

    public void endTurn() {
        actionsManager.executeActions();
        actionsManager.removeAllActions();
        turnProcessor.processTurn();
        aiController.makeTurn();
        gameStateData.setActions(actionsManager.getActions());
        updateInfoAreas();

    }
}
