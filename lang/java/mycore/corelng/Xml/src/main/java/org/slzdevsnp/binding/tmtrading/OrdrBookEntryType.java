//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.01 at 01:25:50 PM CEST 
//


package org.slzdevsnp.binding.tmtrading;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for OrdrBookEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrdrBookEntryType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.deutsche-boerse.com/m7/v6}ClgHseCodeListType">
 *       &lt;attribute name="ordrId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
 *       &lt;attribute name="qty" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quantityType" />
 *       &lt;attribute name="px" use="required" type="{http://www.deutsche-boerse.com/m7/v6}priceType" />
 *       &lt;attribute name="ordrEntryTime" use="required" type="{http://www.deutsche-boerse.com/m7/v6}dateTimeType" />
 *       &lt;attribute name="ordrExeRestriction" type="{http://www.deutsche-boerse.com/m7/v6}ordrExeRestrictionUnionType" />
 *       &lt;attribute name="ordrType" type="{http://www.deutsche-boerse.com/m7/v6}ordrUnionType" />
 *       &lt;attribute name="openCloseInd" type="{http://www.deutsche-boerse.com/m7/v6}openCloseIndUnionType" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrdrBookEntryType")
public class OrdrBookEntryType
    extends ClgHseCodeListType
{

    @XmlAttribute(name = "ordrId", required = true)
    protected long ordrId;
    @XmlAttribute(name = "qty", required = true)
    protected int qty;
    @XmlAttribute(name = "px", required = true)
    protected long px;
    @XmlAttribute(name = "ordrEntryTime", required = true)
    protected XMLGregorianCalendar ordrEntryTime;
    @XmlAttribute(name = "ordrExeRestriction")
    protected String ordrExeRestriction;
    @XmlAttribute(name = "ordrType")
    protected String ordrType;
    @XmlAttribute(name = "openCloseInd")
    protected String openCloseInd;

    /**
     * Gets the value of the ordrId property.
     * 
     */
    public long getOrdrId() {
        return ordrId;
    }

    /**
     * Sets the value of the ordrId property.
     * 
     */
    public void setOrdrId(long value) {
        this.ordrId = value;
    }

    /**
     * Gets the value of the qty property.
     * 
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets the value of the qty property.
     * 
     */
    public void setQty(int value) {
        this.qty = value;
    }

    /**
     * Gets the value of the px property.
     * 
     */
    public long getPx() {
        return px;
    }

    /**
     * Sets the value of the px property.
     * 
     */
    public void setPx(long value) {
        this.px = value;
    }

    /**
     * Gets the value of the ordrEntryTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrdrEntryTime() {
        return ordrEntryTime;
    }

    /**
     * Sets the value of the ordrEntryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrdrEntryTime(XMLGregorianCalendar value) {
        this.ordrEntryTime = value;
    }

    /**
     * Gets the value of the ordrExeRestriction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdrExeRestriction() {
        return ordrExeRestriction;
    }

    /**
     * Sets the value of the ordrExeRestriction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdrExeRestriction(String value) {
        this.ordrExeRestriction = value;
    }

    /**
     * Gets the value of the ordrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdrType() {
        return ordrType;
    }

    /**
     * Sets the value of the ordrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdrType(String value) {
        this.ordrType = value;
    }

    /**
     * Gets the value of the openCloseInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenCloseInd() {
        return openCloseInd;
    }

    /**
     * Sets the value of the openCloseInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenCloseInd(String value) {
        this.openCloseInd = value;
    }

}
