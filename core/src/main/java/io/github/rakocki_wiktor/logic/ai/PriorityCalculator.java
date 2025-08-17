package io.github.rakocki_wiktor.logic.ai;

import io.github.rakocki_wiktor.logic.actions.Action;
import io.github.rakocki_wiktor.logic.actions.AttackAction;
import io.github.rakocki_wiktor.logic.actions.MoveUnitsAction;
import io.github.rakocki_wiktor.logic.actions.RecruitAction;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

import java.util.Objects;

public class PriorityCalculator {


    public static int calculate(Action action) {
        switch (action.getType()) {
            case ATTACK -> {
                AttackAction attackAction = (AttackAction) action;
                return calculateAttackPriority(attackAction);
            }

            case RECRUIT -> {
                RecruitAction recruitAction = (RecruitAction) action;
                return calculateRecruitPriority(recruitAction);
            }

            case MOVE -> {
                MoveUnitsAction moveUnitsAction = (MoveUnitsAction) action;
                return calculateMovePriority(moveUnitsAction);
            }
        }

        return 0;
    }

    private static boolean isGapClosing(Province attacking, Province candidateToAttack) {


        long myNeighbours = candidateToAttack.getNeighbours().stream()
            .filter(p -> p.getNation() == attacking.getNation())
            .count();

        return myNeighbours >= 2;
    }

    private static int calculateAttackPriority(AttackAction action) {

        Province attacking = action.getProvinceAttacking();
        Province attacked = action.getProvinceAttacked();

        int attackingArmy = attacking.getArmySize();
        int attackedArmy = attacked.getArmySize();

        int advantage = attackingArmy - attackedArmy;
        int basePriority = 50;
        int bonus = isGapClosing(attacking, attacked) ? advantage * 2 : 0;

        int priority = basePriority + Math.max(advantage, 0) + bonus;


        return Math.max(priority, 1);
    }

    private static int calculateRecruitPriority(RecruitAction action) {
        Province province = action.getProvince();
        int recruitable = province.getRecruitablePopulation();

        int basePriority = 30;
        int borderBonus = (province.isBorderProvince()) ? 10 : 0;

        return basePriority + recruitable + borderBonus;
    }

    private static int calculateMovePriority(MoveUnitsAction action) {
        Province sourceProvince = action.getSourceProvince();
        Province destinationProvince = action.getDestinationProvince();

        int averageHostileArmySize = 0;

        for (Province hostileNeighbour : destinationProvince.getHostileProvinces()) {
            averageHostileArmySize += hostileNeighbour.getArmySize();
        }

        averageHostileArmySize /= (destinationProvince.getHostileProvinces().isEmpty()) ?
            1 : destinationProvince.getHostileProvinces().size();

        int priority = 10;

        if (destinationProvince.isBorderProvince()) priority += 20;
        if (destinationProvince.getArmySize() < averageHostileArmySize) priority += 20;
        priority += sourceProvince.getArmySize() - destinationProvince.getArmySize();
        int difference = sourceProvince.getArmySize() - destinationProvince.getArmySize();

        return priority + difference;
    }
}
