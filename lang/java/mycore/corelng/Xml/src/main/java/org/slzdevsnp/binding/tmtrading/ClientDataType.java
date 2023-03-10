//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.01 at 01:25:50 PM CEST 
//


package org.slzdevsnp.binding.tmtrading;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * 
 * For order management transactions the client data fields in this section can be used by the client to store information or meta-data about a request. None of this information is used by the backend and it is returned to the client with the response.
 *             
 * 
 * <p>Java class for ClientDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="clientDataInt" type="{http://www.deutsche-boerse.com/m7/v6}intType" />
 *       &lt;attribute name="clientDataString" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
 *       &lt;attribute name="clientCorrelationId" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientDataType")
public class ClientDataType {

    @XmlAttribute(name = "clientDataInt")
    protected Integer clientDataInt;
    @XmlAttribute(name = "clientDataString")
    protected String clientDataString;
    @XmlAttribute(name = "clientCorrelationId")
    protected String clientCorrelationId;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the clientDataInt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClientDataInt() {
        return clientDataInt;
    }

    /**
     * Sets the value of the clientDataInt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClientDataInt(Integer value) {
        this.clientDataInt = value;
    }

    /**
     * Gets the value of the clientDataString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDataString() {
        return clientDataString;
    }

    /**
     * Sets the value of the clientDataString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDataString(String value) {
        this.clientDataString = value;
    }

    /**
     * Gets the value of the clientCorrelationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientCorrelationId() {
        return clientCorrelationId;
    }

    /**
     * Sets the value of the clientCorrelationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientCorrelationId(String value) {
        this.clientCorrelationId = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
