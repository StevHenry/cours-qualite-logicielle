package fr.imt.coffeemachine.machine.component;

import fr.imt.coffeemachine.machine.exception.MaxVolumeLimitReachedException;
import fr.imt.coffeemachine.machine.exception.MinVolumeLimitReachedException;

public class Tank {
    private final double maxVolume;
    private final double minVolume;
    private double actualVolume;

    /**
     * Réservoir d'eau de la cafetière.
     *
     * @param initialVolume Volume à mettre dans le réservoir à sa création
     * @param minVolume     Volume minimal du réservoir
     * @param maxVolume     Volume maximal du réservoir
     */
    public Tank(double initialVolume, double minVolume, double maxVolume) {
        this.maxVolume = maxVolume;
        this.minVolume = minVolume;
        this.actualVolume = initialVolume;
    }

    /**
     * Réduit le volume de matière dans le réservoir
     *
     * @param volumeToDecrease Volume de matière à enlever dans le réservoir
     */
    public void decreaseVolumeInTank(double volumeToDecrease) throws MinVolumeLimitReachedException {
        if (actualVolume - volumeToDecrease < minVolume) {
            throw new MinVolumeLimitReachedException();
        } else {
            this.actualVolume -= volumeToDecrease;
        }
    }

    /**
     * Augmente le volume de matière dans le réservoir
     *
     * @param volumeToIncrease Volume de matière à ajouter dans le réservoir
     */
    public void increaseVolumeInTank(double volumeToIncrease) throws MaxVolumeLimitReachedException {
        if (actualVolume + volumeToIncrease > maxVolume) {
            throw new MaxVolumeLimitReachedException();
        } else {
            this.actualVolume += volumeToIncrease;
        }
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public double getMinVolume() {
        return minVolume;
    }

    public double getActualVolume() {
        return actualVolume;
    }
}
