package org.slzdevsnp.countries.dto.countriesa;


import org.slzdevsnp.binding.countriesa.Country;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Countries")
public class Countries {

    List<Country> countries;

    public List<Country> getCountries(){
        return countries;
    }

    //element that is going to marshalled in the xml
    @XmlElement (name ="Country")
    public void setCountries(List<Country> countries){
        this.countries = countries;
    }

     /** This method is not used by jaxb, just used for business reasons. In the case that this class
     * would be generated using xml schemas definitions, this method has to be added to the
     * generated class or to some helper or util one
     *
             * @param country
     */
    public void add( Country country )
    {
        if( this.countries == null )
        {
            this.countries = new ArrayList<Country>();
        }
        this.countries.add( country );
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("{");
        for( Country c : this.countries )
        {
            str.append( c.getCountryName() +"(" + c.getCountryCapital() +")"+",");
        }
        str.append("}");
        return str.toString();
    }
}
