package io.github.rakocki_wiktor.logic;

import io.github.rakocki_wiktor.logic.actions.ActionsManager;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.ui.UIEventListener;
import io.github.rakocki_wiktor.ui.UIManager;

public class GameController {

    private ActionsManager actionsManager;
    private Province selectedProvince;
    private UIEventListener uiEventListener;
    boolean attacking = false;

    public GameController() {
        actionsManager = new ActionsManager();
    }

    public void setUiEventListener(UIEventListener uiEventListener) {
        this.uiEventListener = uiEventListener;
    }

    public void onAttackButtonClick() {
        attacking = true;
        uiEventListener.showArmySelection();

        if (selectedProvince != null) {
            uiEventListener.setArmySliderMax(selectedProvince.getArmySize());
        }

        uiEventListener.hideAttackButton();
    }

    public void onProvinceClick(Province clickedProvince) {

        if (selectedProvince != null) {
            selectedProvince.setSelected(false);
        }

        if (attacking && selectedProvince.getNeighbours().contains(clickedProvince)) {
            int armySize = uiEventListener.getArmySliderValue();
            selectedProvince.setArmySize(selectedProvince.getArmySize() - armySize);
            actionsManager.addAction(new AttackAction(selectedProvince, clickedProvince, armySize));
            clickedProvince.setAttacked(true);
            attacking = false;
            uiEventListener.hideArmySelection();
        } else {
            uiEventListener.hideArmySelection();
            attacking = false;
            selectedProvince = clickedProvince;
            clickedProvince.setSelected(true);
            uiEventListener.showAttackButton();
        }


    }

    public void endTurn() {
        actionsManager.executeActions();
        actionsManager.removeAllActions();
    }
}
