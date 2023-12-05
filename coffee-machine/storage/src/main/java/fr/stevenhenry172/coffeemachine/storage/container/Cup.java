package fr.stevenhenry172.coffeemachine.storage.container;
public class Cup extends Container {

    public Cup(double capacity){
        super(capacity);
    }

    public Cup(Container container){
        super(container);
    }
}
