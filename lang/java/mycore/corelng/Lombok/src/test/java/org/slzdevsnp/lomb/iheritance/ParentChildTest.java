package org.slzdevsnp.lomb.iheritance;

import org.junit.jupiter.api.Test;
import org.slzdevsnp.lomb.inheritance.Child;
import org.slzdevsnp.lomb.inheritance.Parent;

public class ParentChildTest {

    @Test
    public void testParent(){
        Parent p = new Parent("aaa", 100);
        assert(p!=null);
    }

    @Test
    public void testChild(){
        Child c = new Child("bbbb", 100, "childish");
        assert(c.getPi()==100);
        assert(c.getCd()=="childish");
    }
}
