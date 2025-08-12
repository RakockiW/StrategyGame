package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class RecruitAction implements Action{

    private Province province;
    private int recruitedPopulation;

    public RecruitAction(Province province, int recruitedPopulation) {
        this.province = province;
        this.recruitedPopulation = recruitedPopulation;
    }
    public void execute() {
        province.setArmySize(province.getArmySize() + recruitedPopulation);
    }
}
