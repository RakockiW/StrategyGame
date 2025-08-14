package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class AttackAction implements Action {
    private Province provinceAttacking, provinceAttacked;
    private int attackingArmySize;

    public AttackAction(Province provinceAttacking, Province provinceAttacked, int attackingArmySize) {
        this.provinceAttacking = provinceAttacking;
        this.provinceAttacked = provinceAttacked;
        this.attackingArmySize = attackingArmySize;
    }

    public void win(Province winner, Province loser) {
        loser.setArmySize(attackingArmySize - loser.getArmySize());
        loser.setNation(winner.getNation());
        winner.getNation().addProvince(loser);
        loser.setAttacked(false);
    }

    public void lose(Province winner, Province loser) {
        winner.setArmySize(winner.getArmySize() - attackingArmySize);
        winner.setAttacked(false);
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
}
