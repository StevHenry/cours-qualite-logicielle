package fr.imt.coffeemachine.machine.exception;

public class LackOfWaterInTankException extends Exception{
    public LackOfWaterInTankException(String message) {
        super(message);
    }
}
