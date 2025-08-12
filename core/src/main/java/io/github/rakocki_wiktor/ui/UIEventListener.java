package io.github.rakocki_wiktor.ui;

public interface UIEventListener {
    void showAttackButton();
    void hideAttackButton();
    void showArmySelection();
    void hideArmySelection();
    int getArmySliderValue();
    void setArmySliderMax(int max);
}
