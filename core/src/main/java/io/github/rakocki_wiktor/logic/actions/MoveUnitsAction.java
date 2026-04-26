package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class MoveUnitsAction implements Action{



    private final Province sourceProvince, destinationProvince;
    int armySize;

    public MoveUnitsAction(Province sourceProvince, Province destinationProvince, int armySize) {
        this.sourceProvince = sourceProvince;
        this.destinationProvince = destinationProvince;
        this.armySize = armySize;
    }

    public Province getSourceProvince() {
        return sourceProvince;
    }

    public Province getDestinationProvince() {
        return destinationProvince;
    }

    public int getArmySize() { return armySize; }

    public boolean canExecute() {
        return armySize < sourceProvince.getArmySize() && sourceProvince.getNation().getActionPoints() > 0;
    }


    @Override
    public void execute() {
        destinationProvince.setArmySize(destinationProvince.getArmySize() + armySize);
    }

    public void preTurn() {
        sourceProvince.getNation().removeActionPoints(1);
        sourceProvince.setArmySize(sourceProvince.getArmySize() - armySize);
    }

    public ActionType getType() {
        return ActionType.MOVE;
    }
}
