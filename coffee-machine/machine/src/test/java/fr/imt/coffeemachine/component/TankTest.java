package fr.imt.coffeemachine.component;

import fr.imt.coffeemachine.machine.component.BeanTank;
import fr.imt.coffeemachine.machine.component.Tank;
import fr.imt.coffeemachine.machine.component.WaterTank;
import fr.imt.coffeemachine.machine.exception.MaxVolumeLimitReachedException;
import fr.imt.coffeemachine.machine.exception.MinVolumeLimitReachedException;
import fr.imt.coffeemachine.storage.type.CoffeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TankTest {

    private Tank tank;
    private BeanTank beantank;
    private WaterTank watertank;

    @BeforeEach
    public void resetTank(){
        tank = new Tank(50.0, 15.0, 100.0);
        beantank = new BeanTank(50.0, 15.0, 100.0, CoffeeType.ARABICA);
        watertank = new WaterTank(50.0, 15.0, 100.0);
    }

    @Test
    public void testIncreaseVolume() throws MaxVolumeLimitReachedException {
        double volume1 = tank.getActualVolume();
        double volume2 = beantank.getActualVolume();
        double volume3 = watertank.getActualVolume();

        tank.increaseVolumeInTank(30.0);
        beantank.increaseVolumeInTank(30.0);
        watertank.increaseVolumeInTank(30.0);

        Assertions.assertEquals(volume1 + 30.0, tank.getActualVolume());
        Assertions.assertEquals(volume2 + 30.0, beantank.getActualVolume());
        Assertions.assertEquals(volume3 + 30.0, watertank.getActualVolume());
    }

    @Test
    public void testDecreaseVolume() throws MinVolumeLimitReachedException  {
        double volume1 = tank.getActualVolume();
        double volume2 = beantank.getActualVolume();
        double volume3 = watertank.getActualVolume();

        tank.decreaseVolumeInTank(10.0);
        beantank.decreaseVolumeInTank(10.0);
        watertank.decreaseVolumeInTank(10.0);

        Assertions.assertEquals(volume1 - 10.0, tank.getActualVolume());
        Assertions.assertEquals(volume2 - 10.0, beantank.getActualVolume());
        Assertions.assertEquals(volume3 - 10.0, watertank.getActualVolume());
    }

    @Test
    public void testTankLimits() {
        Assertions.assertThrows(MinVolumeLimitReachedException.class,
                () -> tank.decreaseVolumeInTank(500));
        Assertions.assertThrows(MaxVolumeLimitReachedException.class,
                () -> tank.increaseVolumeInTank(500));
    }

}
