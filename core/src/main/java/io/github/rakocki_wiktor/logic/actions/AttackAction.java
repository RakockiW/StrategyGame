package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

public class AttackAction implements Action {



    private final Province provinceAttacking, provinceAttacked;
    private int attackingArmySize;

    public AttackAction(Province provinceAttacking, Province provinceAttacked, int attackingArmySize) {
        this.provinceAttacking = provinceAttacking;
        this.provinceAttacked = provinceAttacked;
        this.attackingArmySize = attackingArmySize;
    }

    public Province getProvinceAttacking() {
        return provinceAttacking;
    }

    public Province getProvinceAttacked() {
        return provinceAttacked;
    }

    public int getAttackingArmySize() { return attackingArmySize; }

    public void win(Province winner, Province loser) {

        Nation newOwner = winner.getNation();
        Nation oldOwner = loser.getNation();

        if (oldOwner != null) {
            oldOwner.getOwnedProvinces().remove(loser);
        }

        loser.setArmySize(attackingArmySize - loser.getArmySize());
        loser.setNation(newOwner);
        newOwner.addProvince(loser);

        loser.setAttacked(false);
    }

    public void lose(Province winner, Province loser) {
        winner.setArmySize(winner.getArmySize() - attackingArmySize);
        winner.setAttacked(false);
    }

    public boolean canExecute() {
        return attackingArmySize <= provinceAttacking.getArmySize() &&
            provinceAttacking.getNation().getGold() >= attackingArmySize &&
            provinceAttacking.getNation().getActionPoints() > 0;
    }

    @Override
    public void execute() {
        int attackedArmySize = provinceAttacked.getArmySize();

        Province winner;
        Province loser;

        if (attackingArmySize > attackedArmySize) {
            winner = provinceAttacking;
            loser = provinceAttacked;
            win(winner, loser);
        } else {
            winner = provinceAttacked;
            loser = provinceAttacking;
            lose(winner, loser);
        }

    }

    public void preTurn() {
        provinceAttacking.getNation().removeActionPoints(1);
        provinceAttacking.setArmySize(provinceAttacking.getArmySize() - attackingArmySize);
    }

    public ActionType getType() {
        return ActionType.ATTACK;
    }

}
