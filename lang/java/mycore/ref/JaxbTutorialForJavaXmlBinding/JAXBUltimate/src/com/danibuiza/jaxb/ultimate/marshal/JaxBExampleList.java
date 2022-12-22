package com.danibuiza.jaxb.ultimate.marshal;

import java.io.File;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.danibuiza.jaxb.ultimate.business.Countries;
import com.danibuiza.jaxb.ultimate.business.Country;



/**
 * Simple example of usage of jaxb marshaling functionalities showing how to manage lists
 * 
 * @author dgutierrez-diez
 */
public class JaxBExampleList
{

    public static void main( String[] args )
    {
        try
        {

            /* init a list with a couple of countries to marshal */
            Country spain = new Country();
            spain.setName( "Spain" );
            spain.setCapital( "Madrid" );
            spain.setContinent( "Europe" );

            spain.setFoundation( LocalDate.of( 1469, 10, 19 ) );

            Country usa = new Country();
            usa.setName( "USA" );
            usa.setCapital( "Washington" );
            usa.setContinent( "America" );


            usa.setFoundation( LocalDate.of( 1776, 7, 4 ) );

            Countries countries = new Countries();
            countries.add( spain );
            countries.add( usa );

            /* init jaxb marshaler */
            JAXBContext jaxbContext = JAXBContext.newInstance( Countries.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* set this flag to true to format the output */
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            /* marshaling of java objects in xml (output to file and standard output) */
            jaxbMarshaller.marshal( countries, new File( "list_countries.xml" ) );
            jaxbMarshaller.marshal( countries, System.out );

        }
        catch( JAXBException e )
        {
            e.printStackTrace();
        }

    }
}
