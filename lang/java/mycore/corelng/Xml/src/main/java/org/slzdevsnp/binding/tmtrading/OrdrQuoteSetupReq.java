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
 *       &lt;attribute name="usrId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}intType" />
 *       &lt;attribute name="ordrReqActive" use="required" type="{http://www.deutsche-boerse.com/m7/v6}boolType" />
 *       &lt;attribute name="sendMail" use="required" type="{http://www.deutsche-boerse.com/m7/v6}boolType" />
 *       &lt;attribute name="sendSMS" use="required" type="{http://www.deutsche-boerse.com/m7/v6}boolType" />
 *       &lt;attribute name="mailAddress" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
 *       &lt;attribute name="mobileNumber" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
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
@XmlRootElement(name = "OrdrQuoteSetupReq")
public class OrdrQuoteSetupReq {

    @XmlElement(name = "StandardHeader", required = true)
    protected StandardHeaderType standardHeader;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "usrId", required = true)
    protected int usrId;
    @XmlAttribute(name = "ordrReqActive", required = true)
    protected boolean ordrReqActive;
    @XmlAttribute(name = "sendMail", required = true)
    protected boolean sendMail;
    @XmlAttribute(name = "sendSMS", required = true)
    protected boolean sendSMS;
    @XmlAttribute(name = "mailAddress")
    protected String mailAddress;
    @XmlAttribute(name = "mobileNumber")
    protected String mobileNumber;
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
     * Gets the value of the usrId property.
     * 
     */
    public int getUsrId() {
        return usrId;
    }

    /**
     * Sets the value of the usrId property.
     * 
     */
    public void setUsrId(int value) {
        this.usrId = value;
    }

    /**
     * Gets the value of the ordrReqActive property.
     * 
     */
    public boolean isOrdrReqActive() {
        return ordrReqActive;
    }

    /**
     * Sets the value of the ordrReqActive property.
     * 
     */
    public void setOrdrReqActive(boolean value) {
        this.ordrReqActive = value;
    }

    /**
     * Gets the value of the sendMail property.
     * 
     */
    public boolean isSendMail() {
        return sendMail;
    }

    /**
     * Sets the value of the sendMail property.
     * 
     */
    public void setSendMail(boolean value) {
        this.sendMail = value;
    }

    /**
     * Gets the value of the sendSMS property.
     * 
     */
    public boolean isSendSMS() {
        return sendSMS;
    }

    /**
     * Sets the value of the sendSMS property.
     * 
     */
    public void setSendSMS(boolean value) {
        this.sendSMS = value;
    }

    /**
     * Gets the value of the mailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * Sets the value of the mailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailAddress(String value) {
        this.mailAddress = value;
    }

    /**
     * Gets the value of the mobileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the value of the mobileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNumber(String value) {
        this.mobileNumber = value;
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
