package com.monotonic.generics._7_reflection.baeldung_reflection_guide;

import java.util.ArrayList;
import java.util.List;

public class Bird extends Animal{
    private boolean walks;

    //3 constructors, default and 2 non-default
    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        setWalks(walks);
    }

    public Bird(String name) {
        super(name);
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }

    public boolean walks() {
        return walks;
    }

    @Override
    protected String getSound() {
        return "chirik chirik";
    }


    @Override
    public String eats() {
        return "seeds";
    }


    public void printFlightDistances(List<?extends Number> distances){
        distances.stream()
                .forEach(d -> System.out.println("distance: " + d));
    }

}
