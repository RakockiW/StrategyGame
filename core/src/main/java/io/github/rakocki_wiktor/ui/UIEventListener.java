package io.github.rakocki_wiktor.ui;

public interface UIEventListener {
    void showAttackButton();
    void hideAttackButton();
    void showRecruitButton();
    void hideRecruitButton();
    void showSelectionSlider();
    void hideSelectionSlider();
    int getSelectionSliderValue();
    void setSelectionSliderMax(int max);
    void showProvinceInfoArea();
    void hideProvinceInfoArea();
    void updateProvinceInfoArea(String owner, int armySize, int population);
}
