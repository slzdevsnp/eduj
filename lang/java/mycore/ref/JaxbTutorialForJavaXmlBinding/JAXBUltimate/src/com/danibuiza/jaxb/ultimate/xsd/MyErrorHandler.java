package com.danibuiza.jaxb.ultimate.xsd;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Implementation of the interface {@link ErrorHandler}
 * @author dgutierrez-diez
 *
 */
public class MyErrorHandler implements ErrorHandler
{

    @Override
    public void warning( SAXParseException exception ) throws SAXException
    {
        throw exception;

    }

    @Override
    public void error( SAXParseException exception ) throws SAXException
    {
        throw exception;

    }

    @Override
    public void fatalError( SAXParseException exception ) throws SAXException
    {
        throw exception;

    }

}
