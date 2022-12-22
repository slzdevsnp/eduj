package com.danibuiza.jaxb.ultimate.adapter;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class adapts the LocalDate objects by implementing the XmlAdapter interface in order that
 * JaxB is capable of marshal and unmarshal them
 * 
 * @author dgutierrez-diez
 */
public class DateAdapter extends XmlAdapter<String, LocalDate>
{

    /**
     * Overrides the unmarshal method of the class XmlAdapter in order to parse a date of the type
     * LocalDate
     * 
     * @param date String
     * @return LocalDate
     * @throws Exception
     */
    public LocalDate unmarshal( String date ) throws Exception
    {
        return LocalDate.parse( date );
    }

    /**
     * Overrides the marshal method of the class XmlAdapter in order to convert the passed date to
     * an String
     *
     * @param date LocalDate
     * @return String
     * @throws Exception
     */
    public String marshal( LocalDate date ) throws Exception
    {
        return date.toString();
    }

}
