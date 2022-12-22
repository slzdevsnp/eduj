package org.slzdevsnp.countries.marshal;

import org.slzdevsnp.binding.countriesa.Country;
import org.slzdevsnp.binding.countriesa.ObjectFactory;
import org.slzdevsnp.countries.dto.countriesa.Countries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxBExampleList {

    public static void main(String[] args) {
        try{
            ObjectFactory of = new ObjectFactory();
            Country spain = of.createCountry();
            spain.setCountryName( "Spain" );
            spain.setCountryCapital( "Madrid" );
            spain.setCountryContinent( "Europe" );
            spain.setCountryFoundationDate ("1469-10-19");

            Country usa = of.createCountry();;
            usa.setCountryName( "USA" );
            usa.setCountryCapital( "Washington" );
            usa.setCountryContinent( "America" );
            usa.setCountryFoundationDate("1776-07-04");

            Countries countries = new Countries();
            countries.add(spain);
            countries.add(usa);

            //jaxb marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance( Countries.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* set this flag to true to format the output */
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            /* marshaling of java objects in xml (output to file and standard output) */
            jaxbMarshaller.marshal( countries, new File( "src/main/resources/out/list_countriesa.xml"));
            jaxbMarshaller.marshal( countries, System.out );


        }catch( JAXBException e){
            e.printStackTrace();
        }
    }
}
