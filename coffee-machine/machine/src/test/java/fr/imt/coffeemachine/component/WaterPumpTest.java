package fr.imt.coffeemachine.component;

import fr.imt.coffeemachine.machine.component.WaterPump;
import fr.imt.coffeemachine.machine.component.WaterTank;
import fr.imt.coffeemachine.machine.exception.MinVolumeLimitReachedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaterPumpTest {

    private WaterPump waterPump;
    private WaterTank waterTank;

    @BeforeEach
    public void resetTank(){
        waterPump = new WaterPump(10);
        waterTank = new WaterTank(10.0, 0.0, 50.0);
    }

    @Test
    void testPumpWater() throws InterruptedException, MinVolumeLimitReachedException {
        waterPump.pumpWater(1.0, waterTank);
        Assertions.assertEquals(waterTank.getActualVolume(),9.0);
    }

}
