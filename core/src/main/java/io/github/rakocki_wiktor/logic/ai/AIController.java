package io.github.rakocki_wiktor.logic.ai;

import io.github.rakocki_wiktor.logic.actions.*;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

import java.util.*;

public class AIController {

    // Creates list of possible actions
    // Calculates priority of each action
    // Finds the best one and adds it to ActionsManager

    private final GameStateData gameStateData;
    private ArrayList<Nation> nations;
    private ActionsManager actionsManager;
    private Nation playerNation;

    public AIController(GameStateData gameStateData, ActionsManager actionsManager) {
        this.nations = gameStateData.getNations();
        this.actionsManager = actionsManager;
        this.gameStateData = gameStateData;
    }

    public void makeTurn() {

        for (Nation nation : nations) {

            if (nation == gameStateData.getPlayer().getNation()) continue;
            System.out.println(gameStateData.getPlayer().getNation().getId());

            ArrayList<Action> possibleActions = getPossibleActions(nation);
            List<Action> sortedActions = sortByPriority(possibleActions);

            for (Action action : sortedActions) {
                if (action.canExecute()) {
                    action.preTurn();
                    actionsManager.addAction(action);
                }
            }
        }

    }

    private ArrayList<Action> getPossibleActions(Nation nation) {
        ArrayList<Action> possibleActions = new ArrayList<>();

        for (Province province : nation.getOwnedProvinces()) {
            for (Province neighbour : province.getNeighbours()) {

                Action action;

                if (neighbour.getNation() == province.getNation()) {
                    action = new MoveUnitsAction(province, neighbour, province.getArmySize());
                } else {
                    action = new AttackAction(province, neighbour, province.getArmySize());
                }
                if (action.canExecute()) possibleActions.add(action);
            }

            RecruitAction action = new RecruitAction(province, province.getRecruitablePopulation());
            if (action.canExecute()) possibleActions.add(action);

        }
        return possibleActions;
    }

    private List<Action> sortByPriority(ArrayList<Action> actions) {
        return actions.stream()
        .sorted(Comparator.comparingInt(PriorityCalculator::calculate).reversed())
        .toList();
    }

}
