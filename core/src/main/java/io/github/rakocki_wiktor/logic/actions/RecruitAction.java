package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class RecruitAction implements Action{

    private Province province;
    private int recruitedPopulation;

    public RecruitAction(Province province, int recruitedPopulation) {
        this.province = province;
        this.recruitedPopulation = recruitedPopulation;
    }

    public Province getProvince() {
        return province;
    }

    public boolean canExecute() {
        return recruitedPopulation > 0 &&
            province.getNation().getGold() >= recruitedPopulation &&
            province.getNation().getActionPoints() > 0;
    }


    public void execute() {
        province.setArmySize(province.getArmySize() + recruitedPopulation);
    }

    @Override
    public void preTurn() {
        province.getNation().removeActionPoints(1);
        province.getNation().setGold(province.getNation().getGold() - recruitedPopulation);
    }

    public ActionType getType() {
        return ActionType.RECRUIT;
    }
}
