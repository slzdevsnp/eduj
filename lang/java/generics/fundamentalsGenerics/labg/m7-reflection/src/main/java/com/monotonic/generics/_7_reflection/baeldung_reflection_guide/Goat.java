package com.monotonic.generics._7_reflection.baeldung_reflection_guide;

public class Goat extends Animal implements Locomotion {

    //constr
    public Goat(){
        super("goat");
    }
    public Goat(String name){
        super(name);
    }

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String eats() {
        return "grass";
    }


}