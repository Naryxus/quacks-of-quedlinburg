package main;

public class Player {

    private int victoryPoints;

    private Bag bag;

    private Kettle kettle;

    public Player(int[] victoryPoints, int[] qualityPoints, int maxCherryBombPoints) {
        this.victoryPoints = 0;
        bag = new Bag();
        kettle = new Kettle(victoryPoints, qualityPoints, maxCherryBombPoints);
    }
}
