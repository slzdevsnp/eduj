package org.slzdevsnp.enums;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class OrderStatefulTest {
    private OrderStateful order;

    @Before
    public void init(){
        order = new OrderStateful(1, OrderStateful.OrderState.INITIATED);
    }

    @Test
    public void testDeliveryTime(){
        assertThat(order.isDelivered(), not(true));
        assertThat(order.getDeliveryTimeInDays(), is(5));
    }
}
