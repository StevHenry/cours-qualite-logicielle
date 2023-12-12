package fr.imt.coffeemachine.component;

import fr.imt.coffeemachine.machine.component.BeanTank;
import fr.imt.coffeemachine.machine.component.CoffeeGrinder;
import fr.imt.coffeemachine.machine.exception.MinVolumeLimitReachedException;
import fr.imt.coffeemachine.storage.type.CoffeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeGrinderTest {
    private CoffeeGrinder coffeeGrinder;
    private BeanTank beanTank;

    @BeforeEach
    public void resetTank(){
        coffeeGrinder = new CoffeeGrinder(10);
        beanTank = new BeanTank(15.0, 0.0, 50.0, CoffeeType.MOKA);
    }

    @Test
    void testGrind() throws InterruptedException, MinVolumeLimitReachedException {
        coffeeGrinder.grindCoffee(beanTank);
        Assertions.assertEquals(beanTank.getActualVolume(),14.8);
    }
}