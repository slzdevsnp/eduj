package com.danibuiza.jaxb.ultimate.xsd;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

/**
 * Implementation of the interface {@link ValidationEventHandler}
 * @author dgutierrez-diez
 *
 */
public class MyValidationEventHandler implements ValidationEventHandler
{

    @Override
    public boolean handleEvent( ValidationEvent event )
    {
        System.out.println( "Error catched!!" );
        System.out.println( "event.getSeverity():  " + event.getSeverity() );
        System.out.println( "event:  " + event.getMessage() );
        System.out.println( "event.getLinkedException():  " + event.getLinkedException() );
        //the locator contains much more information like line, column, etc.
        System.out.println( "event.getLocator().getObject():  " + event.getLocator().getObject() );
        return false;
    }

}
