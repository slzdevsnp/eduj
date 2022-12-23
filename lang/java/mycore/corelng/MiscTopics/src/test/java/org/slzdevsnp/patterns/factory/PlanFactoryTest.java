package org.slzdevsnp.patterns.factory;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class PlanFactoryTest {

    @Test
    public void checkDomesticPlan(){
        Plan plan = PlanFactory.createPlan(PlanFactory.ContractPlan.DOMESTIC);
        System.out.println(plan);
        assertThat(plan.getRate(), is(3.5));
        assertThat(plan, isA(DomesticPlan.class) );
    }

    @Test
    public void checkCommecialPlan(){
        Plan plan = PlanFactory.createPlan(PlanFactory.ContractPlan.COMMERCIAL);
        assertThat(plan.getRate(), is(7.5));
        assertThat(plan, isA(CommercialPlan.class) );
    }

}
