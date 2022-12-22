package org.slzdevsnp.lomb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoggerAnnotated {

    @Test
    public void testLoggerAnnotated(){
        LoggerAnnotated  mobj = new LoggerAnnotated();
        mobj.say();
        mobj.cry();
        assertTrue(mobj != null);
    }

}
