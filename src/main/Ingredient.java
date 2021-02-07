package main;

import java.util.Objects;

public abstract class Ingredient {

    private final int quality;

    public Ingredient(int quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quality);
    }

    public int getQuality() {
        return quality;
    }

    public abstract String toString();
}
