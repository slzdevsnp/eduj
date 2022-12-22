package org.slzdevsnp.countries.unmarshal;

import org.slzdevsnp.countries.dto.countriesa.Countries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UnMarshalJAXVBCompleteExample {

    public static void main(String[] args) {
        try
        {

            File file = new File( "src/main/resources/in/countriesa.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance( Countries.class );

            /**
             * the only difference with the marshaling operation is here
             */
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Countries countres = (Countries)jaxbUnmarshaller.unmarshal( file );
            System.out.println( countres );

        }
        catch( JAXBException e )
        {
            e.printStackTrace();
        }
    }

}
