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
 * <p>Java class for OrdrListEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrdrListEntryType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.deutsche-boerse.com/m7/v6}ClgHseCodeAcctListType">
 *       &lt;attGroup ref="{http://www.deutsche-boerse.com/m7/v6}OrdrAttrGrp"/>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrdrListEntryType")
public class OrdrListEntryType
    extends ClgHseCodeAcctListType
{

    @XmlAttribute(name = "ordrId", required = true)
    protected long ordrId;
    @XmlAttribute(name = "initialOrdrId", required = true)
    protected long initialOrdrId;
    @XmlAttribute(name = "parentOrdrId")
    protected Long parentOrdrId;
    @XmlAttribute(name = "clearingAcctType")
    protected String clearingAcctType;
    @XmlAttribute(name = "acctId", required = true)
    protected String acctId;
    @XmlAttribute(name = "contractId", required = true)
    protected long contractId;
    @XmlAttribute(name = "side", required = true)
    protected String side;
    @XmlAttribute(name = "px", required = true)
    protected long px;
    @XmlAttribute(name = "stopPx")
    protected Long stopPx;
    @XmlAttribute(name = "ppd")
    protected Long ppd;
    @XmlAttribute(name = "qty", required = true)
    protected int qty;
    @XmlAttribute(name = "hiddenQty")
    protected Integer hiddenQty;
    @XmlAttribute(name = "displayQty")
    protected Integer displayQty;
    @XmlAttribute(name = "initialQty", required = true)
    protected int initialQty;
    @XmlAttribute(name = "ordrExeRestriction")
    protected String ordrExeRestriction;
    @XmlAttribute(name = "txt")
    protected String txt;
    @XmlAttribute(name = "dlvryAreaId", required = true)
    protected String dlvryAreaId;
    @XmlAttribute(name = "clOrdrId")
    protected String clOrdrId;
    @XmlAttribute(name = "preArranged", required = true)
    protected boolean preArranged;
    @XmlAttribute(name = "preArrangedAcct")
    protected String preArrangedAcct;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "state", required = true)
    protected String state;
    @XmlAttribute(name = "aggressorIndicator")
    protected String aggressorIndicator;
    @XmlAttribute(name = "usrCode", required = true)
    protected String usrCode;
    @XmlAttribute(name = "revisionNo", required = true)
    protected long revisionNo;
    @XmlAttribute(name = "timestmp", required = true)
    protected XMLGregorianCalendar timestmp;
    @XmlAttribute(name = "validityDate")
    protected XMLGregorianCalendar validityDate;
    @XmlAttribute(name = "validityRes")
    protected String validityRes;
    @XmlAttribute(name = "action", required = true)
    protected String action;
    @XmlAttribute(name = "lastUpdateUsrInfo", required = true)
    protected String lastUpdateUsrInfo;
    @XmlAttribute(name = "openCloseInd")
    protected String openCloseInd;
    @XmlAttribute(name = "brokerUsrId")
    protected Integer brokerUsrId;
    @XmlAttribute(name = "counterOrder")
    protected Boolean counterOrder;
    @XmlAttribute(name = "remoteOrdrId")
    protected Long remoteOrdrId;
    @XmlAttribute(name = "lastUpdateTm", required = true)
    protected XMLGregorianCalendar lastUpdateTm;
    @XmlAttribute(name = "preAotId")
    protected Long preAotId;
    @XmlAttribute(name = "aot")
    protected Boolean aot;

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
     * Gets the value of the initialOrdrId property.
     * 
     */
    public long getInitialOrdrId() {
        return initialOrdrId;
    }

    /**
     * Sets the value of the initialOrdrId property.
     * 
     */
    public void setInitialOrdrId(long value) {
        this.initialOrdrId = value;
    }

    /**
     * Gets the value of the parentOrdrId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentOrdrId() {
        return parentOrdrId;
    }

    /**
     * Sets the value of the parentOrdrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentOrdrId(Long value) {
        this.parentOrdrId = value;
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
     * Gets the value of the stopPx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStopPx() {
        return stopPx;
    }

    /**
     * Sets the value of the stopPx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStopPx(Long value) {
        this.stopPx = value;
    }

    /**
     * Gets the value of the ppd property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPpd() {
        return ppd;
    }

    /**
     * Sets the value of the ppd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPpd(Long value) {
        this.ppd = value;
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
     * Gets the value of the hiddenQty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHiddenQty() {
        return hiddenQty;
    }

    /**
     * Sets the value of the hiddenQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHiddenQty(Integer value) {
        this.hiddenQty = value;
    }

    /**
     * Gets the value of the displayQty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayQty() {
        return displayQty;
    }

    /**
     * Sets the value of the displayQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayQty(Integer value) {
        this.displayQty = value;
    }

    /**
     * Gets the value of the initialQty property.
     * 
     */
    public int getInitialQty() {
        return initialQty;
    }

    /**
     * Sets the value of the initialQty property.
     * 
     */
    public void setInitialQty(int value) {
        this.initialQty = value;
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
     * Gets the value of the clOrdrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClOrdrId() {
        return clOrdrId;
    }

    /**
     * Sets the value of the clOrdrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClOrdrId(String value) {
        this.clOrdrId = value;
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
     * Gets the value of the preArrangedAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreArrangedAcct() {
        return preArrangedAcct;
    }

    /**
     * Sets the value of the preArrangedAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreArrangedAcct(String value) {
        this.preArrangedAcct = value;
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
     * Gets the value of the aggressorIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggressorIndicator() {
        return aggressorIndicator;
    }

    /**
     * Sets the value of the aggressorIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggressorIndicator(String value) {
        this.aggressorIndicator = value;
    }

    /**
     * Gets the value of the usrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsrCode() {
        return usrCode;
    }

    /**
     * Sets the value of the usrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsrCode(String value) {
        this.usrCode = value;
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
     * Gets the value of the validityDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidityDate() {
        return validityDate;
    }

    /**
     * Sets the value of the validityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidityDate(XMLGregorianCalendar value) {
        this.validityDate = value;
    }

    /**
     * Gets the value of the validityRes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidityRes() {
        return validityRes;
    }

    /**
     * Sets the value of the validityRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidityRes(String value) {
        this.validityRes = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the lastUpdateUsrInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdateUsrInfo() {
        return lastUpdateUsrInfo;
    }

    /**
     * Sets the value of the lastUpdateUsrInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdateUsrInfo(String value) {
        this.lastUpdateUsrInfo = value;
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
     * Gets the value of the brokerUsrId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBrokerUsrId() {
        return brokerUsrId;
    }

    /**
     * Sets the value of the brokerUsrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBrokerUsrId(Integer value) {
        this.brokerUsrId = value;
    }

    /**
     * Gets the value of the counterOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCounterOrder() {
        if (counterOrder == null) {
            return false;
        } else {
            return counterOrder;
        }
    }

    /**
     * Sets the value of the counterOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCounterOrder(Boolean value) {
        this.counterOrder = value;
    }

    /**
     * Gets the value of the remoteOrdrId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoteOrdrId() {
        return remoteOrdrId;
    }

    /**
     * Sets the value of the remoteOrdrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoteOrdrId(Long value) {
        this.remoteOrdrId = value;
    }

    /**
     * Gets the value of the lastUpdateTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateTm() {
        return lastUpdateTm;
    }

    /**
     * Sets the value of the lastUpdateTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateTm(XMLGregorianCalendar value) {
        this.lastUpdateTm = value;
    }

    /**
     * Gets the value of the preAotId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPreAotId() {
        return preAotId;
    }

    /**
     * Sets the value of the preAotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPreAotId(Long value) {
        this.preAotId = value;
    }

    /**
     * Gets the value of the aot property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAot() {
        if (aot == null) {
            return false;
        } else {
            return aot;
        }
    }

    /**
     * Sets the value of the aot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAot(Boolean value) {
        this.aot = value;
    }

}
