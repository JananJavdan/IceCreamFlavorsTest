package be.intecbrussel.IceCreamFlavors.eatables;

import java.util.Arrays;

public class Cone implements Eatable {
    private Flavor[] balls;

    public Cone() {
        this.balls = new Flavor[]{Flavor.CHOCOLATE};
    }

    public Cone(Flavor[] balls) {
        this.balls = balls;
    }

    public Flavor[] getBalls() {
        return balls;
    }

    @Override
    public void eat() {
        System.out.println("We are eating a " + Arrays.toString(balls) + " cone");
    }

    public enum Flavor {
        STRAWBERRY,
        BANANA,
        CHOCOLATE,
        VANILLA,
        LEMON,
        STRACIATELLA,
        MOKKA,
        PISTACHE;
    }
}
