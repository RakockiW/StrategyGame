package io.github.rakocki_wiktor.logic;

import io.github.rakocki_wiktor.logic.actions.ActionsManager;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.logic.actions.RecruitAction;
import io.github.rakocki_wiktor.model.Player;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.ui.UIEventListener;

public class GameController {

    private final Player player;
    private ActionsManager actionsManager;
    private Province selectedProvince;
    private UIEventListener uiEventListener;
    private GameState state = GameState.PICKING_NATION;

    public GameController(Player player) {
        actionsManager = new ActionsManager();
        this.player = player;
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
                uiEventListener.setSelectionSliderMax(selectedProvince.getPopulation());
                state = GameState.RECRUITING;
            }

            case RECRUITING -> {
                int recruitedPopulation = uiEventListener.getSelectionSliderValue();
                if (selectedProvince != null) {
                    uiEventListener.setSelectionSliderMax(selectedProvince.getPopulation());
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
                    actionsManager.addAction(new AttackAction(selectedProvince, clickedProvince, armySize));
                    clickedProvince.setAttacked(true);
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
                uiEventListener.updateProvinceInfoArea(
                    clickedProvince.getNation().getName(),
                    clickedProvince.getArmySize(),
                    clickedProvince.getPopulation());
                uiEventListener.updateNationInfoArea(
                    selectedProvince.getNation().getName(),
                    selectedProvince.getNation().getGold(),
                    selectedProvince.getNation().getProvincesAmount()
                );
                uiEventListener.showProvinceInfoArea();
            }
        }

    }

    public void onPickButtonClick() {

        if (selectedProvince != null) {
            player.setNation(selectedProvince.getNation());
            uiEventListener.showEndTurnButton();
            uiEventListener.hidePickButton();
            state = GameState.IDLE;
        }

    }

    public void endTurn() {
        actionsManager.executeActions();
        actionsManager.removeAllActions();
    }
}
