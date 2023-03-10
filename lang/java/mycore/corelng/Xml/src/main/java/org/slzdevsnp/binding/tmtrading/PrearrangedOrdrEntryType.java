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
 * <p>Java class for PrearrangedOrdrEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrearrangedOrdrEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.deutsche-boerse.com/m7/v6}PreArrangedAttrGrp"/>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrearrangedOrdrEntryType")
public class PrearrangedOrdrEntryType {

    @XmlAttribute(name = "traderUserId", required = true)
    protected int traderUserId;
    @XmlAttribute(name = "acctId", required = true)
    protected String acctId;
    @XmlAttribute(name = "clearingAcctType")
    protected String clearingAcctType;
    @XmlAttribute(name = "dlvryAreaId")
    protected String dlvryAreaId;
    @XmlAttribute(name = "openCloseInd")
    protected String openCloseInd;
    @XmlAttribute(name = "brokerUserId")
    protected Integer brokerUserId;
    @XmlAttribute(name = "clgAcctId")
    protected Integer clgAcctId;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the traderUserId property.
     * 
     */
    public int getTraderUserId() {
        return traderUserId;
    }

    /**
     * Sets the value of the traderUserId property.
     * 
     */
    public void setTraderUserId(int value) {
        this.traderUserId = value;
    }

    /**
     * Gets the value of the acctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctId(String value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the clearingAcctType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearingAcctType() {
        return clearingAcctType;
    }

    /**
     * Sets the value of the clearingAcctType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearingAcctType(String value) {
        this.clearingAcctType = value;
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

    /**
     * Gets the value of the brokerUserId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBrokerUserId() {
        return brokerUserId;
    }

    /**
     * Sets the value of the brokerUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBrokerUserId(Integer value) {
        this.brokerUserId = value;
    }

    /**
     * Gets the value of the clgAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClgAcctId() {
        return clgAcctId;
    }

    /**
     * Sets the value of the clgAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClgAcctId(Integer value) {
        this.clgAcctId = value;
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
