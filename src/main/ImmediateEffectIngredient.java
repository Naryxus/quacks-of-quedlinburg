package main;

public abstract class ImmediateEffectIngredient extends Ingredient {

    private final ImmediateEffect immediateEffect;

    public ImmediateEffectIngredient(int quality, ImmediateEffect immediateEffect) {
        super(quality);
        this.immediateEffect = immediateEffect;
    }

    public void immediateEffect(Bag bag, Kettle kettle) {
        immediateEffect.immediateEffect(bag, kettle);
    }
}
