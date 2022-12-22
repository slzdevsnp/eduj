package org.slzdevsnp.countries.xsd;

import java.io.File;
import java.time.LocalDate;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slzdevsnp.binding.countriesa.Country;
import org.xml.sax.SAXException;


public class JaxBExampleXSDErrorHandler {

    public static void main( String[] args ) throws Exception
    {
        /**
         setCountry         */
        Country spain = new Country();
        spain.setCountryName( "Spain" );
        spain.setCountryCapital( "Madrid" );
        spain.setCountryFoundationDate( "1469-10-19" );
        spain.setCountryContinent("Europe"); //if you foget to specificy continent, validation problem

        /**
         * ok
         */
        Country australia = new Country();
        australia.setCountryName( "Australia" );
        australia.setCountryCapital( "Camberra" );
        australia.setCountryFoundationDate( "1778-01-26" );
        australia.setCountryContinent( "Oceania" );

        /**
         * schema is created
         */
        SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        Schema schema = sf.newSchema( new File( "src/main/resources/countries_validation.xsd" ) );

        /**
         * context is created and used to create sources for each country
         */
        JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );
        JAXBSource sourceSpain = new JAXBSource( jaxbContext, spain );
        JAXBSource sourceAustralia = new JAXBSource( jaxbContext, australia );

        /**
         * validator is initialized
         */
        Validator validator = schema.newValidator();
        validator.setErrorHandler( new CustomXmlErrorHandler() );

        //validator is used
        try
        {
            validator.validate( sourceSpain );
            System.out.println( "spain has no problems" );
        }
        catch( SAXException ex )
        {
            ex.printStackTrace();
            System.out.println( "spain has problems" );
        }
        try
        {
            validator.validate( sourceAustralia );
            System.out.println( "australia has no problems" );
        }
        catch( SAXException ex )
        {
            ex.printStackTrace();
            System.out.println( "australia has problems" );
        }
    }
}
