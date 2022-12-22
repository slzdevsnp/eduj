package org.slzdevsnp.countries.xsd;

import org.slzdevsnp.binding.countriesa.Country;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class JaxBExampleXSD {
    public static void main( String[] args ) throws Exception
    {
        Country spain = new Country();
        spain.setCountryName( "Spain" );
        spain.setCountryCapital( "Madrid" );
        spain.setCountryContinent( "Europe" );
        spain.setCountryFoundationDate( "1469-10-19" );
        spain.setCountryPopulation( 45000000 );



        SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        Schema schema = sf.newSchema( new File( "src/main/resources/countries.xsd" ) );

        JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        marshaller.setSchema( schema );
        marshaller.marshal( spain, System.out );
    }



}
