//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.01 at 01:25:50 PM CEST 
//


package org.slzdevsnp.binding.tmtrading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StandardHeader" type="{http://www.deutsche-boerse.com/m7/v6}StandardHeaderType"/>
 *         &lt;any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ordrId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
 *       &lt;attribute name="px" use="required" type="{http://www.deutsche-boerse.com/m7/v6}priceType" />
 *       &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
 *       &lt;attribute name="qty" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quantityType" />
 *       &lt;attribute name="side" use="required" type="{http://www.deutsche-boerse.com/m7/v6}directionUnionType" />
 *       &lt;attribute name="dlvryAreaId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}deliveryAreaIdType" />
 *       &lt;attribute name="timestmp" use="required" type="{http://www.deutsche-boerse.com/m7/v6}dateTimeType" />
 *       &lt;attribute name="reqTime" use="required" type="{http://www.deutsche-boerse.com/m7/v6}dateTimeType" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "standardHeader",
    "any"
})
@XmlRootElement(name = "OrdrQuoteRprt")
public class OrdrQuoteRprt {

    @XmlElement(name = "StandardHeader", required = true)
    protected StandardHeaderType standardHeader;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "ordrId", required = true)
    protected long ordrId;
    @XmlAttribute(name = "px", required = true)
    protected long px;
    @XmlAttribute(name = "contractId", required = true)
    protected long contractId;
    @XmlAttribute(name = "qty", required = true)
    protected int qty;
    @XmlAttribute(name = "side", required = true)
    protected String side;
    @XmlAttribute(name = "dlvryAreaId", required = true)
    protected String dlvryAreaId;
    @XmlAttribute(name = "timestmp", required = true)
    protected XMLGregorianCalendar timestmp;
    @XmlAttribute(name = "reqTime", required = true)
    protected XMLGregorianCalendar reqTime;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the standardHeader property.
     * 
     * @return
     *     possible object is
     *     {@link StandardHeaderType }
     *     
     */
    public StandardHeaderType getStandardHeader() {
        return standardHeader;
    }

    /**
     * Sets the value of the standardHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link StandardHeaderType }
     *     
     */
    public void setStandardHeader(StandardHeaderType value) {
        this.standardHeader = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

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
     * Gets the value of the side property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSide() {
        return side;
    }

    /**
     * Sets the value of the side property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSide(String value) {
        this.side = value;
    }

    /**
     * Gets the value of the dlvryAreaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlvryAreaId() {
        return dlvryAreaId;
    }

    /**
     * Sets the value of the dlvryAreaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlvryAreaId(String value) {
        this.dlvryAreaId = value;
    }

    /**
     * Gets the value of the timestmp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestmp() {
        return timestmp;
    }

    /**
     * Sets the value of the timestmp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestmp(XMLGregorianCalendar value) {
        this.timestmp = value;
    }

    /**
     * Gets the value of the reqTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqTime() {
        return reqTime;
    }

    /**
     * Sets the value of the reqTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqTime(XMLGregorianCalendar value) {
        this.reqTime = value;
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
