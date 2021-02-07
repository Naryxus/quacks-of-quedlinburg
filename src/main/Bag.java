package main;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class Bag {

    private final Map<Ingredient, Integer> content;

    private int size;

    private final Random random;

    public Bag() {
        content = new HashMap<>();
        size = 0;
        random = new Random();
    }

    public void addIngredient(Ingredient ingredient) {
        content.compute(ingredient, (ingr, val) -> {
            if (val == null)
                    return 1;
            return val + 1;
        });
        size++;
    }

    public Ingredient drawIngredient() {
        if (size == 0) throw new NoSuchElementException("There is no ingredient in your bag.");
        Ingredient ingredient = sample();
        if (content.get(ingredient) == 1) content.remove(ingredient);
        else content.compute(ingredient, (ingr, val) -> {
            if (val == null) throw new IllegalArgumentException("There is no quantity for the ingredient \"" + ingr + "\"");
            return val - 1;
        });
        size--;
        return ingredient;
    }

    private Ingredient sample() {
        int randomValue = random.nextInt(size);
        int sum = 0;
        for (Map.Entry<Ingredient, Integer> entry : content.entrySet()) {
            int ingredientQuantity = entry.getValue();
            sum += ingredientQuantity;
            if (randomValue < sum) return entry.getKey();
        }
        throw new RuntimeException("Something went wrong in \"Bag.sample()\".");
    }

    @Override
    public String toString() {
        return "Bag{" +
                "content=" + content +
                '}';
    }

    public static void main(String[] args) {
        Bag bag = new Bag();

        System.out.println(bag);

        bag.addIngredient(new Pumpkin(3));

        System.out.println(bag);

        bag.addIngredient(new Pumpkin(6));

        System.out.println(bag);

        bag.addIngredient(new Pumpkin(3));

        System.out.println(bag);

        System.out.println("Drawn " + bag.drawIngredient());
        System.out.println(bag);

        System.out.println("Drawn " + bag.drawIngredient());
        System.out.println(bag);
    }
}
