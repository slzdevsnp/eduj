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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for BasicQuoteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicQuoteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
 *       &lt;attribute name="sellPx" type="{http://www.deutsche-boerse.com/m7/v6}priceType" />
 *       &lt;attribute name="sellQty" type="{http://www.deutsche-boerse.com/m7/v6}quantityType" />
 *       &lt;attribute name="buyPx" type="{http://www.deutsche-boerse.com/m7/v6}priceType" />
 *       &lt;attribute name="buyQty" type="{http://www.deutsche-boerse.com/m7/v6}quantityType" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicQuoteType")
@XmlSeeAlso({
    QuoteType.class
})
public class BasicQuoteType {

    @XmlAttribute(name = "contractId", required = true)
    protected long contractId;
    @XmlAttribute(name = "sellPx")
    protected Long sellPx;
    @XmlAttribute(name = "sellQty")
    protected Integer sellQty;
    @XmlAttribute(name = "buyPx")
    protected Long buyPx;
    @XmlAttribute(name = "buyQty")
    protected Integer buyQty;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the contractId property.
     * 
     */
    public long getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     */
    public void setContractId(long value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the sellPx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSellPx() {
        return sellPx;
    }

    /**
     * Sets the value of the sellPx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSellPx(Long value) {
        this.sellPx = value;
    }

    /**
     * Gets the value of the sellQty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSellQty() {
        return sellQty;
    }

    /**
     * Sets the value of the sellQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSellQty(Integer value) {
        this.sellQty = value;
    }

    /**
     * Gets the value of the buyPx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBuyPx() {
        return buyPx;
    }

    /**
     * Sets the value of the buyPx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBuyPx(Long value) {
        this.buyPx = value;
    }

    /**
     * Gets the value of the buyQty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuyQty() {
        return buyQty;
    }

    /**
     * Sets the value of the buyQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuyQty(Integer value) {
        this.buyQty = value;
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
