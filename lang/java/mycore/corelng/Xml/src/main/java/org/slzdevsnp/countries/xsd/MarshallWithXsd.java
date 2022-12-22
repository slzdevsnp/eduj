package org.slzdevsnp.countries.xsd;

import org.slzdevsnp.binding.countriesa.Country;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class MarshallWithXsd{

    public static void main(String[] args) throws SAXException, JAXBException {

        Country spain = new Country();
        spain.setCountryName( "Spain" );
        spain.setCountryCapital( "Madrid" );
        spain.setCountryFoundationDate( "1469-10-19" );
        spain.setCountryContinent("Europe"); //if you foget to specificy continent, validation problem

        SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        Schema schema = sf.newSchema( new File( "src/main/resources/countries_validation.xsd" ));

        JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        marshaller.setSchema( schema );
        //the schema uses a validation handler for validate the objects
        marshaller.setEventHandler( new MyValidationEventHandler() );
        try
        {
            marshaller.marshal( spain, System.out );
        }
        catch( JAXBException ex )
        {
            ex.printStackTrace();
        }

    }

}
