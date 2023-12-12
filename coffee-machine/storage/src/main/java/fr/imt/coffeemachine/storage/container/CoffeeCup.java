package fr.imt.coffeemachine.storage.container;

import fr.imt.coffeemachine.storage.type.CoffeeType;

public class CoffeeCup extends CoffeeContainer{
    public CoffeeCup(double capacity, CoffeeType coffeeType) {
        super(capacity, coffeeType);
    }

    public CoffeeCup(Cup cup, CoffeeType coffeeType) {
        super(cup, coffeeType);
    }
}
