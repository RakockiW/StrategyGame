package io.github.rakocki_wiktor.logic;

import io.github.rakocki_wiktor.logic.actions.ActionsManager;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.logic.actions.RecruitAction;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.ui.UIEventListener;

public class GameController {

    private ActionsManager actionsManager;
    private Province selectedProvince;
    private UIEventListener uiEventListener;
    boolean attacking = false;
    boolean recruiting = false;

    public GameController() {
        actionsManager = new ActionsManager();
    }

    public void setUiEventListener(UIEventListener uiEventListener) {
        this.uiEventListener = uiEventListener;
    }

    public void onAttackButtonClick() {
        attacking = true;
        uiEventListener.showSelectionSlider();

        if (selectedProvince != null) {
            uiEventListener.setSelectionSliderMax(selectedProvince.getArmySize());
        }
        uiEventListener.hideAttackButton();
        uiEventListener.hideRecruitButton();
    }

    public void onRecruitButtonClick() {
        if (!recruiting) {
            uiEventListener.showSelectionSlider();
            uiEventListener.setSelectionSliderMax(selectedProvince.getPopulation());
            recruiting = true;
        } else {
            int recruitedPopulation = uiEventListener.getSelectionSliderValue();

            if (selectedProvince != null) {
                uiEventListener.setSelectionSliderMax(selectedProvince.getPopulation());
                selectedProvince.setPopulation(selectedProvince.getPopulation() - recruitedPopulation);
            }

            actionsManager.addAction(new RecruitAction(selectedProvince, recruitedPopulation));
            recruiting = false;
            uiEventListener.hideSelectionSlider();
        }

    }

    public void onProvinceClick(Province clickedProvince) {

        if (selectedProvince != null) {
            selectedProvince.setSelected(false);
        }

        if (attacking && selectedProvince.getNeighbours().contains(clickedProvince)) {
            int armySize = uiEventListener.getSelectionSliderValue();
            selectedProvince.setArmySize(selectedProvince.getArmySize() - armySize);
            actionsManager.addAction(new AttackAction(selectedProvince, clickedProvince, armySize));
            clickedProvince.setAttacked(true);
            attacking = false;
            uiEventListener.hideSelectionSlider();
        } else {
            uiEventListener.hideSelectionSlider();
            attacking = false;
            selectedProvince = clickedProvince;
            clickedProvince.setSelected(true);
            uiEventListener.showProvinceInfoArea();
            uiEventListener.updateProvinceInfoArea(
                selectedProvince.getOwner().getName(),
                selectedProvince.getArmySize(),
                selectedProvince.getPopulation());
            uiEventListener.showAttackButton();
            uiEventListener.showRecruitButton();
        }


    }

    public void endTurn() {
        actionsManager.executeActions();
        actionsManager.removeAllActions();
    }
}
