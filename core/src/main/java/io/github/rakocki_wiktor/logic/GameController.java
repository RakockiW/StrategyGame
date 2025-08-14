package io.github.rakocki_wiktor.logic;

import com.badlogic.gdx.Game;
import io.github.rakocki_wiktor.logic.actions.ActionsManager;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.logic.actions.MoveUnitsAction;
import io.github.rakocki_wiktor.logic.actions.RecruitAction;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Player;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.ui.UIEventListener;

import java.util.ArrayList;

public class GameController {

    private final Player player;
    private ActionsManager actionsManager;
    private Province selectedProvince;
    private UIEventListener uiEventListener;
    private GameState state = GameState.PICKING_NATION;
    private GameTurnProcessor turnProcessor;

    public GameController(GameStateData gameStateData) {
        actionsManager = new ActionsManager();
        this.player = gameStateData.getPlayer();
        this.turnProcessor = new GameTurnProcessor(gameStateData);
    }

    public void setUiEventListener(UIEventListener uiEventListener) {
        this.uiEventListener = uiEventListener;
    }

    public void onAttackButtonClick() {
        state = GameState.ATTACKING;

        uiEventListener.showSelectionSlider();
        if (selectedProvince != null) {
            uiEventListener.setSelectionSliderMax(selectedProvince.getArmySize());
        }

        uiEventListener.hideAttackButton();
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
                actionsManager.addAction(new RecruitAction(selectedProvince, recruitedPopulation));
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
            case ATTACKING -> {
                if (selectedProvince.getNeighbours().contains(clickedProvince)) {
                    int armySize = uiEventListener.getSelectionSliderValue();
                    selectedProvince.setArmySize(selectedProvince.getArmySize() - armySize);

                    if (clickedProvince.getNation() == player.getNation()) {
                        actionsManager.addAction(new MoveUnitsAction(clickedProvince, armySize));
                    } else {
                        actionsManager.addAction(new AttackAction(selectedProvince, clickedProvince, armySize));
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
                uiEventListener.updateProvinceInfoArea
                    (
                        selectedProvince.getNation().getName(),
                        selectedProvince.getArmySize(),
                        selectedProvince.getPopulation()
                    );
                uiEventListener.updateNationInfoArea(
                    selectedProvince.getNation().getName(),
                    selectedProvince.getNation().getGold(),
                    selectedProvince.getNation().getProvincesAmount()
                );
                if (selectedProvince.getNation() == player.getNation()) {
                    uiEventListener.showAttackButton();
                    uiEventListener.showRecruitButton();
                } else {
                    uiEventListener.hideAttackButton();
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
            selectedProvince.getNation().getProvincesAmount()
        );
    }

    public void endTurn() {
        turnProcessor.processTurn();
        actionsManager.executeActions();
        actionsManager.removeAllActions();
        updateInfoAreas();

    }
}
