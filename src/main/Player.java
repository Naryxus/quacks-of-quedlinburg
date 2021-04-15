package main;

import java.util.List;

public class Player {

    private int victoryPoints;

    private final Bag bag;

    private final Kettle kettle;

    private final DecisionInterface decisionInterface;

    public Player(int[] victoryPoints,
                  int[] qualityPoints,
                  int maxCherryBombPoints,
                  List<Ingredient> ingredients,
                  DecisionInterface decisionInterface) {
        this.victoryPoints = 0;
        bag = new Bag(ingredients);
        kettle = new Kettle(victoryPoints, qualityPoints, maxCherryBombPoints);
        this.decisionInterface = decisionInterface;
    }

    public Bag bag() {
        return bag;
    }

    public Kettle kettle() {
        return kettle;
    }

    public DecisionInterface iface() {
        return decisionInterface;
    }

    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }

    public int victoryPoints() {
        return victoryPoints;
    }
}
