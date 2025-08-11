package io.github.rakocki_wiktor.logic.actions;

import io.github.rakocki_wiktor.model.Province;

public class AttackAction implements Action {
    private Province provinceAttacking, provinceAttacked;

    public AttackAction(Province provinceAttacking, Province provinceAttacked) {
        this.provinceAttacking = provinceAttacking;
        this.provinceAttacked = provinceAttacked;
    }

    public void result(Province winner, Province loser) {
        winner.setArmySize(provinceAttacking.getArmySize() - provinceAttacked.getArmySize());
        loser.setArmySize(provinceAttacking.getArmySize());
        loser.setOwner(winner.getOwner());
        loser.setAttacked(false);
    }

    @Override
    public void execute() {
        int attackingArmySize = provinceAttacking.getArmySize();
        int attackedArmySize = provinceAttacked.getArmySize();

        if (attackingArmySize > attackedArmySize) {
            Province winner = provinceAttacking;
            Province loser = provinceAttacked;

            result(winner, loser);
        } else {
            Province winner = provinceAttacked;
            Province loser = provinceAttacking;

            result(winner, loser);
        }
    }
}
