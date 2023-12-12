package fr.imt.coffeemachine.cucumber.steps;

import fr.imt.coffeemachine.machine.CoffeeMachine;
import fr.imt.coffeemachine.machine.exception.CannotMakeCremaWithSimpleCoffeeMachine;
import fr.imt.coffeemachine.machine.exception.CoffeeTypeCupDifferentOfCoffeeTypeTankException;
import fr.imt.coffeemachine.machine.exception.LackOfWaterInTankException;
import fr.imt.coffeemachine.machine.exception.MachineNotPluggedException;
import fr.imt.coffeemachine.storage.type.CoffeeType;
import fr.imt.coffeemachine.storage.container.*;
import fr.imt.coffeemachine.storage.exception.CupNotEmptyException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


public class CucumberStepsCoffeeMachineMakeACoffeeTest {

    public CoffeeMachine coffeeMachine;
    public Mug mug;
    public Cup cup;
    public CoffeeContainer containerWithCoffee;

    @Given("a coffee machine with {double} l of min capacity, {double} l of max capacity, {double} l per h of water flow for the pump")
    public void givenACoffeeMachine(double minimalWaterCapacity, double maximalWaterCapacity, double pumpWaterFlow){
        coffeeMachine = new CoffeeMachine(minimalWaterCapacity, maximalWaterCapacity, minimalWaterCapacity, maximalWaterCapacity, pumpWaterFlow);
    }

    @And("a {string} with a capacity of {double}")
    public void aWithACapacityOf(String containerType, double containerCapacity) {
        if ("mug".equals(containerType))
            mug = new Mug(containerCapacity);
        if ("cup".equals(containerType))
            cup = new Cup(containerCapacity);
    }

    @When("I plug the machine to electricity")
    public void iPlugTheMachineToElectricity() {
        coffeeMachine.plugToElectricalPlug();
    }

    @And("I add {double} liter of water in the water tank")
    public void iAddLitersOfWater(double waterVolume) {
        coffeeMachine.addWaterInTank(waterVolume);
    }

    @And("I add {double} liter of {string} in the bean tank")
    public void iAddLitersOfCoffeeBeans(double beanVolume, String coffeeType) {
        coffeeMachine.addCoffeeInBeanTank(beanVolume, CoffeeType.valueOf(coffeeType));
    }

    @And("I made a coffee {string}")
    public void iMadeACoffee(String coffeeType) throws InterruptedException, CupNotEmptyException, LackOfWaterInTankException, MachineNotPluggedException, CoffeeTypeCupDifferentOfCoffeeTypeTankException, CannotMakeCremaWithSimpleCoffeeMachine {
        //On créé un mock de l'objet random
        Random randomMock = Mockito.mock(Random.class, Mockito.withSettings().withoutAnnotations());
        //On vient ensuite stubber la méthode nextGaussian pour pouvoir controler la valeur retournée
        //ici on veut qu'elle retourne 0.6
        Mockito.when(randomMock.nextGaussian()).thenReturn(0.6);
        //On injecte ensuite le mock créé dans la machine à café
        coffeeMachine.setRandomGenerator(randomMock);

        if (mug != null)
            containerWithCoffee = coffeeMachine.makeACoffee(mug, CoffeeType.valueOf(coffeeType));
        if (cup != null)
            containerWithCoffee = coffeeMachine.makeACoffee(cup, CoffeeType.valueOf(coffeeType));

    }
    
    @Then("the coffee machine return a coffee mug not empty")
    public void theCoffeeMachineReturnACoffeeMugNotEmpty() {
        Assertions.assertFalse(containerWithCoffee.isEmpty());
    }


    @And("a coffee volume equals to {double}")
    public void aCoffeeVolumeEqualsTo(double coffeeVolume) {
        MatcherAssert.assertThat(coffeeVolume, Matchers.is(containerWithCoffee.getCapacity()));
    }

    @And("a coffee {string} containing a coffee type {string}")
    public void aCoffeeMugContainingACoffeeType(String containerType, String coffeeType) {
        if ("mug".equals(containerType))
            MatcherAssert.assertThat(containerWithCoffee, Matchers.instanceOf(CoffeeMug.class));
        if ("cup".equals(containerType))
            MatcherAssert.assertThat(containerWithCoffee, Matchers.instanceOf(CoffeeCup.class));

        MatcherAssert.assertThat(containerWithCoffee.getCoffeeType(), Matchers.is(CoffeeType.valueOf(coffeeType)));
    }


}