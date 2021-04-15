package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameLoop {

    private int round;

    private List<Player> players;

    public GameLoop(List<Player> players) {
        if (players == null || players.isEmpty()) throw new IllegalArgumentException("The list of players must contain at least one player.");

        this.players = players;

        round = 0;
    }

    public void mainLoop() {
        // Preparation Phase

        for (Player player : players) {
            while (true) {
                if (player.kettle().isExploded()) break;

                AnswerType drawNextIngredient = player.iface().drawNextIngredient();
                if (drawNextIngredient == AnswerType.Abort) return;
                if (drawNextIngredient == AnswerType.Deny) break;

                if (drawNextIngredient == AnswerType.Confirm) player.kettle().placeIngredient(player.bag().drawIngredient());
            }
        }

        System.out.println();
        System.out.println("Alle Spieler haben die Zubereitungsphase beendet. Die Wertungsphase beginnt.");
        System.out.println();

        // Evaluation Phase

        for (Player player : players) {
            for (Ingredient ingredient : player.kettle().placedIngredients())
                if (ingredient instanceof DelayedEffectIngredient)
                    ((DelayedEffectIngredient) ingredient).delayedEffect();
        }

        List<Player> buyingPlayers = new LinkedList<>();

        for (Player player : players) {
            int victoryPoints = player.kettle().victoryPoints();
            if (player.kettle().isExploded()) {
                System.out.println("Da Dein Kessel explodiert ist, musst du dich entscheiden, ob Du Siegpunkte nehmen möchtest oder Einkaufen gehen möchtest.");
                System.out.println("Du würdest " + victoryPoints + (victoryPoints == 1 ? " Siegpunkt" : " Siegpunkte") + " bekommen.");

                AnswerType takeVictoryPoints = player.iface().takeVictoryPoints();

                if (takeVictoryPoints == AnswerType.Abort) return;
                if (takeVictoryPoints == AnswerType.Deny) {
                    buyingPlayers.add(player);
                    System.out.println("Du hast auf die Siegpunkte verzichtet und kannst stattdessen gleich einkaufen.");
                }
                if (takeVictoryPoints == AnswerType.Confirm) {
                    player.addVictoryPoints(victoryPoints);
                    System.out.println("Du hast " +
                            (victoryPoints == 1 ? "den einen Siegpunkt" : "die " + victoryPoints + " Siegpunkte") +
                            " gewählt und hast damit jetzt insgesamt " +
                            (player.victoryPoints() == 1 ? "1 Siegpunkt" : player.victoryPoints() + " Siegpunkte") +
                            ".");
                }
            } else {
                buyingPlayers.add(player);
                player.addVictoryPoints(victoryPoints);
                System.out.println("Du erhältst " +
                        (victoryPoints == 1 ? "einen Siegpunkt" : victoryPoints + " Siegpunkte") +
                        " und hast damit jetzt insgesamt " +
                        (player.victoryPoints() == 1 ? "1 Siegpunkt" : player.victoryPoints() + " Siegpunkte") +
                        ".");
            }
        }
    }

    public static void main(String[] args) {
        int[] victoryPoints = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6,
                6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 15};
        int[] qualityPoints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 16, 16, 17, 17, 18,
                18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31,
                31, 32, 32, 33, 33, 35};
        int maxCherryBombPoints = 7;

        List<Ingredient> ingredients = Arrays.asList(
                new CherryBomb(1), new CherryBomb(1), new CherryBomb(1), new CherryBomb(2),
                new CherryBomb(2), new CherryBomb(3), new Pumpkin(1));

        CommandLineInterface cli = new CommandLineInterface();

        Player player1 = new Player(victoryPoints, qualityPoints, maxCherryBombPoints, ingredients, cli);

        GameLoop gameLoop = new GameLoop(Collections.singletonList(player1));

        gameLoop.mainLoop();
    }
}
