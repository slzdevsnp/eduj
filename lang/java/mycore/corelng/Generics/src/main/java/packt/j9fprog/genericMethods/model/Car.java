package packt.j9fprog.genericMethods.model;

import packt.j9fprog.genericMethods.TransportInterface;

public class Car implements TransportInterface {
    private final String model;
    private final int maxSpeed;

    public Car(){
        this.model="undefined";
        this.maxSpeed=100;
    }

    public Car(String model, int maxSpeed){
        this.model = model;
        this.maxSpeed = maxSpeed;
    }


    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }
}

