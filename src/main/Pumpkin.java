package main;

public class Pumpkin extends Ingredient {

    public Pumpkin(int quality) {
        super(quality);
    }

    @Override
    public String toString() {
        return "Pumpkin(" + getQuality() + ")";
    }
}
