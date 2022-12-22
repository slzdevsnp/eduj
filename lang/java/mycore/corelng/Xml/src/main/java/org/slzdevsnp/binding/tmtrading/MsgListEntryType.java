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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for MsgListEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MsgListEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VarList" type="{http://www.deutsche-boerse.com/m7/v6}VarListType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.deutsche-boerse.com/m7/v6}MsgAttrGrp"/>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgListEntryType", propOrder = {
    "varList"
})
public class MsgListEntryType {

    @XmlElement(name = "VarList")
    protected VarListType varList;
    @XmlAttribute(name = "msgId", required = true)
    protected long msgId;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "messageCode", required = true)
    protected int messageCode;
    @XmlAttribute(name = "prod")
    protected String prod;
    @XmlAttribute(name = "acctId")
    protected String acctId;
    @XmlAttribute(name = "timestmp", required = true)
    protected XMLGregorianCalendar timestmp;
    @XmlAttribute(name = "svrty", required = true)
    protected String svrty;
    @XmlAttribute(name = "txt", required = true)
    protected String txt;
    @XmlAttribute(name = "sellDlvryAreaId")
    protected String sellDlvryAreaId;
    @XmlAttribute(name = "buyDlvryAreaId")
    protected String buyDlvryAreaId;
    @XmlAttribute(name = "mktSupervisionMsg", required = true)
    protected boolean mktSupervisionMsg;
    @XmlAttribute(name = "nonPersistent")
    protected Boolean nonPersistent;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the varList property.
     * 
     * @return
     *     possible object is
     *     {@link VarListType }
     *     
     */
    public VarListType getVarList() {
        return varList;
    }

    /**
     * Sets the value of the varList property.
     * 
     * @param value
     *     allowed object is
     *     {@link VarListType }
     *     
     */
    public void setVarList(VarListType value) {
        this.varList = value;
    }

    /**
     * Gets the value of the msgId property.
     * 
     */
    public long getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     */
    public void setMsgId(long value) {
        this.msgId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the messageCode property.
     * 
     */
    public int getMessageCode() {
        return messageCode;
    }

    /**
     * Sets the value of the messageCode property.
     * 
     */
    public void setMessageCode(int value) {
        this.messageCode = value;
    }

    /**
     * Gets the value of the prod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProd() {
        return prod;
    }

    /**
     * Sets the value of the prod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProd(String value) {
        this.prod = value;
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
     * Gets the value of the svrty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrty() {
        return svrty;
    }

    /**
     * Sets the value of the svrty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrty(String value) {
        this.svrty = value;
    }

    /**
     * Gets the value of the txt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxt() {
        return txt;
    }

    /**
     * Sets the value of the txt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxt(String value) {
        this.txt = value;
    }

    /**
     * Gets the value of the sellDlvryAreaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellDlvryAreaId() {
        return sellDlvryAreaId;
    }

    /**
     * Sets the value of the sellDlvryAreaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellDlvryAreaId(String value) {
        this.sellDlvryAreaId = value;
    }

    /**
     * Gets the value of the buyDlvryAreaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyDlvryAreaId() {
        return buyDlvryAreaId;
    }

    /**
     * Sets the value of the buyDlvryAreaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyDlvryAreaId(String value) {
        this.buyDlvryAreaId = value;
    }

    /**
     * Gets the value of the mktSupervisionMsg property.
     * 
     */
    public boolean isMktSupervisionMsg() {
        return mktSupervisionMsg;
    }

    /**
     * Sets the value of the mktSupervisionMsg property.
     * 
     */
    public void setMktSupervisionMsg(boolean value) {
        this.mktSupervisionMsg = value;
    }

    /**
     * Gets the value of the nonPersistent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonPersistent() {
        return nonPersistent;
    }

    /**
     * Sets the value of the nonPersistent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonPersistent(Boolean value) {
        this.nonPersistent = value;
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
