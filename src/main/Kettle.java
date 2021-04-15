package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kettle {

    private List<Ingredient> ingredients;

    private List<Integer> ingredientPositions;

    private int[] victoryPoints;

    private int[] qualityPoints;

    private int dropPosition;

    private int nextPosition;

    private int maxCherryBombPoints;

    public Kettle(int[] victoryPoints, int[] qualityPoints, int maxCherryBombPoints) {
        if (victoryPoints.length != qualityPoints.length)
            throw new IllegalArgumentException("The number of victory points ("
                    + victoryPoints.length
                    + ") must be equal to the number of quality points ("
                    + qualityPoints.length
                    + ").");

        this.victoryPoints = victoryPoints;
        this.qualityPoints = qualityPoints;
        this.maxCherryBombPoints = maxCherryBombPoints;

        ingredients = new ArrayList<>();
        ingredientPositions = new ArrayList<>();
        dropPosition = 0;
        nextPosition = 1;
    }

    public void placeIngredient(Ingredient ingredient) {
        nextPosition += ingredient.getQuality();
        ingredients.add(ingredient);
        ingredientPositions.add(nextPosition - 1);
        System.out.println("Dein Kessel sieht wie folgt aus:");
        System.out.println(this);
        if (isExploded()) System.out.println("Dein Kessel ist explodiert.");
        else System.out.println("Im Kessel sind aktuell " + sumCherryBombPoints() + "/" + maxCherryBombPoints + " enthalten.");
    }

    public boolean isExploded() {
        return sumCherryBombPoints() > maxCherryBombPoints;
    }

    public int sumCherryBombPoints() {
        return ingredients.stream()
                .filter(ingredient -> ingredient instanceof CherryBomb)
                .mapToInt(Ingredient::getQuality)
                .sum();
    }

    public List<Ingredient> placedIngredients() {
        return ingredients;
    }

    public List<Integer> ingredientPositions() {
        return ingredientPositions;
    }

    public int victoryPoints() {
        return victoryPoints[nextPosition];
    }

    @Override
    public String toString() {
        String[] positions = new String[getSize()];
        for (int i = 0; i < getSize(); i++) {
            positions[i] = "(" + qualityPoints[i] + ", " + victoryPoints[i] + ")";
        }

        positions[dropPosition] = "Drop";

        for (int i = 0; i < ingredientPositions.size(); i++)
            positions[ingredientPositions.get(i)] = ingredients.get(i).toString();

        return "Kettle{" + Arrays.toString(positions) + "}";
    }

    public int getSize() {
        return victoryPoints.length;
    }

    public static void main(String[] args) {
        Pumpkin pumpkin1 = new Pumpkin(3);
        Pumpkin pumpkin2 = new Pumpkin(6);
        Pumpkin pumpkin3 = new Pumpkin(3);

        int[] victoryPoints = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6,
                6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 15};
        int[] qualityPoints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 16, 16, 17, 17, 18,
                18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31,
                31, 32, 32, 33, 33, 35};

        Kettle kettle = new Kettle(victoryPoints, qualityPoints, 7);
        System.out.println(kettle);

        kettle.placeIngredient(pumpkin1);
        System.out.println(kettle);

        kettle.placeIngredient(pumpkin2);
        System.out.println(kettle);

        kettle.placeIngredient(pumpkin3);
        System.out.println(kettle);

        System.out.println(kettle.isExploded());

        kettle.placeIngredient(new CherryBomb(7));
        System.out.println(kettle);
        System.out.println(kettle.isExploded());

        kettle.placeIngredient(new CherryBomb(1));
        System.out.println(kettle);
        System.out.println(kettle.isExploded());
    }
}
