package org.slzdevsnp.patterns.factory;

public class PlanFactory {

    public enum ContractPlan {
        DOMESTIC, COMMERCIAL, INSTITUTIONAL
    }

    public static Plan createPlan(ContractPlan plan) {
        Plan p = null;
        switch (plan) {
            case DOMESTIC:
                p = new DomesticPlan();
                break;
            case COMMERCIAL:
                p = new CommercialPlan();
                break;
            case INSTITUTIONAL:
                p = new InstitutionalPlan();
                break;
            default:
                p = null;
        }
        if (p != null) {
            p.setRate(); //set the rate
        }
        return p;
    }
}
