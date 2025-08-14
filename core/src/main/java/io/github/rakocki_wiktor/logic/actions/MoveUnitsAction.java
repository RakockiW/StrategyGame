package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class MoveUnitsAction implements Action{

    Province sourceProvince, destinationProvince;
    int armySize;

    public MoveUnitsAction(Province destinationProvince, int armySize) {
        this.destinationProvince = destinationProvince;
        this.armySize = armySize;
    }

    @Override
    public void execute() {
        destinationProvince.setArmySize(destinationProvince.getArmySize() + armySize);
    }
}
