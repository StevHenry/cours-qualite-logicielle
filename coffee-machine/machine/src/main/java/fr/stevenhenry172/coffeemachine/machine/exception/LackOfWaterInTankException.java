package fr.stevenhenry172.coffeemachine.machine.exception;

public class LackOfWaterInTankException extends Exception{
    public LackOfWaterInTankException(String message) {
        super(message);
    }
}
