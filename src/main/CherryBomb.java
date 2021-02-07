package main;

public class CherryBomb extends Ingredient {

    public CherryBomb(int quality) {
        super(quality);
    }

    @Override
    public String toString() {
        return "CherryBomb(" + getQuality() + ")";
    }
}
