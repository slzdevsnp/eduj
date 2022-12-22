package org.slzdevsnp.lomb.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    @Test
    public void testUser(){
        User u0 = new User("John","Wedge", 23);
        String firstName = u0.getFirstName();  // this method is automatically generated
        assertEquals(firstName,"John");
    }

    @Test
    public void testNoArgsUser(){
        User u = new User();  // we did  not speciy no args constructor. yet it is present
        System.out.println("user:"+u);
        assertTrue(u != null);
    }
}
