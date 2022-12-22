package org.slzdevsnp.countries.marshal;

import org.slzdevsnp.binding.countriesa.Country;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxBExampleSimple
{

    public static void main( String[] args )
    {
        try
        {

            /* init very simple data to marshal */
            Country spain = new Country();
            spain.setCountryName( "Spain" );
            spain.setCountryCapital( "Madrid" );
            spain.setCountryContinent( "Europe" );
            spain.setCountryFoundationDate ("1469-10-19");


            spain.setCountryPopulation(45000000);

            /* init jaxb marshaler */
            JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* set this flag to true to format the output */
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            /* marshaling of java objects in xml (output to file and standard output) */
            jaxbMarshaller.marshal( spain, new File( "src/main/resources/out/countrya.xml" ) );
            jaxbMarshaller.marshal( spain, System.out );
        }
        catch( JAXBException e )
        {
            e.printStackTrace();
        }

    }
}
