package fr.imt.coffeemachine.cucumber.steps;

import fr.imt.coffeemachine.machine.CoffeeMachine;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.mockito.Mockito;

import java.util.Random;

public class PlugMachineStep {

    public CoffeeMachine machine;

    @Given("A new coffee machine with {double} L of minimal capacity, {double} L of maximal capacity, {double} L per H of water flow for the pump")
    public void createCoffeeMachine(double minCapa, double maxCapa, double pumpWaterFlow){
        machine = new CoffeeMachine(minCapa, maxCapa, minCapa, maxCapa, pumpWaterFlow);
    }

    @When("The user plugs the new machine")
    public void machineIsConnecting() {
        machine.plugToElectricalPlug();
    }

    @And("The machine is not out of order")
    public void notOutOfOrder(){
        Random randomMock = Mockito.mock(Random.class, Mockito.withSettings().withoutAnnotations());
        Mockito.when(randomMock.nextGaussian()).thenReturn(0.0);
        machine.setRandomGenerator(randomMock);

        MatcherAssert.assertThat(machine.isOutOfOrder(), Matchers.is(false));
    }

    @Then("The machine is connected to the power grid")
    public void machineConnected(){
        MatcherAssert.assertThat(machine.isPlugged(), Matchers.is(true));
    }
}