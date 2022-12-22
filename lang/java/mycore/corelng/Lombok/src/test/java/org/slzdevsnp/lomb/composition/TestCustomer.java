package org.slzdevsnp.lomb.composition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    @Test
    public void testCustomer(){
        Customer c = new Customer();
        c.setFirstName("John");
        c.setLastName("Wane");
        c.setPhoneNr("7675833");

        assertTrue(c instanceof  Customer);
        assertEquals(c.getFullName(),"John Wane");
    }
}
