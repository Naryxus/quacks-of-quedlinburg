package main;

public abstract class DelayedEffectIngredient extends Ingredient {

    private final DelayedEffect delayedEffect;

    public DelayedEffectIngredient(int quality, DelayedEffect delayedEffect) {
        super(quality);

        if (delayedEffect == null) throw new NullPointerException("The delayed effect must not be null.");

        this.delayedEffect = delayedEffect;
    }

    public void delayedEffect() {
        delayedEffect.delayedEffect();
    }
}
