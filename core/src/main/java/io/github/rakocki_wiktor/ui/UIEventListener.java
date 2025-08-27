package io.github.rakocki_wiktor.ui;

public interface UIEventListener {
    void showMainGameTable();
    void showEndTurnButton();
    void showMoveButton();
    void hideMoveButton();
    void showRecruitButton();
    void hideRecruitButton();
    void showSelectionSlider();
    void hideSelectionSlider();
    int getSelectionSliderValue();
    void setSelectionSliderMax(int max);
    void showProvinceInfoArea();
    void hideProvinceInfoArea();
    void updateProvinceInfoArea(String owner, int armySize, int population);
    void updateNationInfoArea(String name, int goldAmount, int provincesAmount, int actionPoints, int totalArmySize);
    void updateRelationInfo(int relation);
    void showPickButton();
    void hidePickButton();
}
