package org.slzdevsnp.patterns.factory;

abstract class  Plan {
    protected double rate;

    abstract void setRate();

    double getRate(){
        return rate;
    }

    double computeAmount(double rate, int units){
        return rate*units;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "rate=" + rate +
                '}';
    }
}
