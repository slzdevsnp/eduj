package com.danibuiza.jaxb.ultimate.business;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.danibuiza.jaxb.ultimate.adapter.DateAdapter;

/**
 * Country containing a name, a capital city, a foundation date and the continent it belongs to,
 * these attributes are going to be represented in this order in the generated XML as described in
 * the annotation XmlType
 * 
 * @author dgutierrez-diez
 */
@XmlType( propOrder = { "name", "capital", "foundation", "continent", "population" } )
@XmlRootElement( name = "Country" )
public class Country
{
    String    name;

    String    capital;

    LocalDate foundation;

    String    continent;

    int       population;

    public int getPopulation()
    {
        return population;
    }

    @XmlElement( name = "Country_Population" )
    public void setPopulation( int population )
    {
        this.population = population;
    }

    public String getName()
    {
        return name;
    }

    @XmlElement( name = "Country_Name" )
    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer( "Name: " + getName() + "\n" );
        str.append( "Capital: " + getCapital() + "\n" );

        if( getFoundation() != null )
        {
            str.append( getFoundation().toString() );
            str.append( "\n" );
        }

        if( getContinent() != null )
        {
            str.append( getContinent().toString() );
            str.append( "\n" );
        }

        return str.toString();
    }

    public String getCapital()
    {
        return capital;
    }

    @XmlElement( name = "Country_Capital" )
    public void setCapital( String capital )
    {
        this.capital = capital;
    }

    public LocalDate getFoundation()
    {
        return foundation;
    }

    @XmlElement( name = "Country_Foundation_Date" )
    @XmlJavaTypeAdapter( DateAdapter.class )
    public void setFoundation( LocalDate foundation )
    {
        this.foundation = foundation;
    }

    public String getContinent()
    {
        return continent;
    }

    @XmlElement( name = "Country_Continent" )
    public void setContinent( String continent )
    {
        this.continent = continent;
    }
}
