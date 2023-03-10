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
 * <p>Java class for TradeListEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TradeListEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Buy" type="{http://www.deutsche-boerse.com/m7/v6}HalfTradeType" minOccurs="0"/>
 *         &lt;element name="Sell" type="{http://www.deutsche-boerse.com/m7/v6}HalfTradeType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.deutsche-boerse.com/m7/v6}trdAttrGrp"/>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeListEntryType", propOrder = {
    "buy",
    "sell"
})
public class TradeListEntryType {

    @XmlElement(name = "Buy")
    protected HalfTradeType buy;
    @XmlElement(name = "Sell")
    protected HalfTradeType sell;
    @XmlAttribute(name = "tradeId", required = true)
    protected long tradeId;
    @XmlAttribute(name = "state", required = true)
    protected String state;
    @XmlAttribute(name = "contractId", required = true)
    protected long contractId;
    @XmlAttribute(name = "qty", required = true)
    protected int qty;
    @XmlAttribute(name = "px", required = true)
    protected long px;
    @XmlAttribute(name = "execTime", required = true)
    protected XMLGregorianCalendar execTime;
    @XmlAttribute(name = "revisionNo", required = true)
    protected long revisionNo;
    @XmlAttribute(name = "preArranged", required = true)
    protected boolean preArranged;
    @XmlAttribute(name = "prearrangeType")
    protected String prearrangeType;
    @XmlAttribute(name = "recallReqTime")
    protected XMLGregorianCalendar recallReqTime;
    @XmlAttribute(name = "recallGrantedTime")
    protected XMLGregorianCalendar recallGrantedTime;
    @XmlAttribute(name = "recallRejectedTime")
    protected XMLGregorianCalendar recallRejectedTime;
    @XmlAttribute(name = "latestRecallProcessTime")
    protected XMLGregorianCalendar latestRecallProcessTime;
    @XmlAttribute(name = "contractPhase", required = true)
    protected String contractPhase;
    @XmlAttribute(name = "clgHseCode")
    protected String clgHseCode;
    @XmlAttribute(name = "parentTradeId")
    protected Long parentTradeId;
    @XmlAttribute(name = "decomposed")
    protected Boolean decomposed;
    @XmlAttribute(name = "remoteTradeId")
    protected Long remoteTradeId;
    @XmlAttribute(name = "selfTrade")
    protected Boolean selfTrade;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the buy property.
     * 
     * @return
     *     possible object is
     *     {@link HalfTradeType }
     *     
     */
    public HalfTradeType getBuy() {
        return buy;
    }

    /**
     * Sets the value of the buy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HalfTradeType }
     *     
     */
    public void setBuy(HalfTradeType value) {
        this.buy = value;
    }

    /**
     * Gets the value of the sell property.
     * 
     * @return
     *     possible object is
     *     {@link HalfTradeType }
     *     
     */
    public HalfTradeType getSell() {
        return sell;
    }

    /**
     * Sets the value of the sell property.
     * 
     * @param value
     *     allowed object is
     *     {@link HalfTradeType }
     *     
     */
    public void setSell(HalfTradeType value) {
        this.sell = value;
    }

    /**
     * Gets the value of the tradeId property.
     * 
     */
    public long getTradeId() {
        return tradeId;
    }

    /**
     * Sets the value of the tradeId property.
     * 
     */
    public void setTradeId(long value) {
        this.tradeId = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
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
     * Gets the value of the execTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecTime() {
        return execTime;
    }

    /**
     * Sets the value of the execTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecTime(XMLGregorianCalendar value) {
        this.execTime = value;
    }

    /**
     * Gets the value of the revisionNo property.
     * 
     */
    public long getRevisionNo() {
        return revisionNo;
    }

    /**
     * Sets the value of the revisionNo property.
     * 
     */
    public void setRevisionNo(long value) {
        this.revisionNo = value;
    }

    /**
     * Gets the value of the preArranged property.
     * 
     */
    public boolean isPreArranged() {
        return preArranged;
    }

    /**
     * Sets the value of the preArranged property.
     * 
     */
    public void setPreArranged(boolean value) {
        this.preArranged = value;
    }

    /**
     * Gets the value of the prearrangeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrearrangeType() {
        return prearrangeType;
    }

    /**
     * Sets the value of the prearrangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrearrangeType(String value) {
        this.prearrangeType = value;
    }

    /**
     * Gets the value of the recallReqTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecallReqTime() {
        return recallReqTime;
    }

    /**
     * Sets the value of the recallReqTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecallReqTime(XMLGregorianCalendar value) {
        this.recallReqTime = value;
    }

    /**
     * Gets the value of the recallGrantedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecallGrantedTime() {
        return recallGrantedTime;
    }

    /**
     * Sets the value of the recallGrantedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecallGrantedTime(XMLGregorianCalendar value) {
        this.recallGrantedTime = value;
    }

    /**
     * Gets the value of the recallRejectedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecallRejectedTime() {
        return recallRejectedTime;
    }

    /**
     * Sets the value of the recallRejectedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecallRejectedTime(XMLGregorianCalendar value) {
        this.recallRejectedTime = value;
    }

    /**
     * Gets the value of the latestRecallProcessTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestRecallProcessTime() {
        return latestRecallProcessTime;
    }

    /**
     * Sets the value of the latestRecallProcessTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestRecallProcessTime(XMLGregorianCalendar value) {
        this.latestRecallProcessTime = value;
    }

    /**
     * Gets the value of the contractPhase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractPhase() {
        return contractPhase;
    }

    /**
     * Sets the value of the contractPhase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractPhase(String value) {
        this.contractPhase = value;
    }

    /**
     * Gets the value of the clgHseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClgHseCode() {
        return clgHseCode;
    }

    /**
     * Sets the value of the clgHseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClgHseCode(String value) {
        this.clgHseCode = value;
    }

    /**
     * Gets the value of the parentTradeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentTradeId() {
        return parentTradeId;
    }

    /**
     * Sets the value of the parentTradeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentTradeId(Long value) {
        this.parentTradeId = value;
    }

    /**
     * Gets the value of the decomposed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDecomposed() {
        return decomposed;
    }

    /**
     * Sets the value of the decomposed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDecomposed(Boolean value) {
        this.decomposed = value;
    }

    /**
     * Gets the value of the remoteTradeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoteTradeId() {
        return remoteTradeId;
    }

    /**
     * Sets the value of the remoteTradeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoteTradeId(Long value) {
        this.remoteTradeId = value;
    }

    /**
     * Gets the value of the selfTrade property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelfTrade() {
        return selfTrade;
    }

    /**
     * Sets the value of the selfTrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelfTrade(Boolean value) {
        this.selfTrade = value;
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
