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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


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
 *         &lt;element name="HubToHubAtcList" type="{http://www.deutsche-boerse.com/m7/v6}HubToHubAtcData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="revisionNo" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
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
    "hubToHubAtcList"
})
@XmlRootElement(name = "HubToHubResp")
public class HubToHubResp {

    @XmlElement(name = "StandardHeader", required = true)
    protected StandardHeaderType standardHeader;
    @XmlElement(name = "HubToHubAtcList")
    protected List<HubToHubAtcData> hubToHubAtcList;
    @XmlAttribute(name = "revisionNo", required = true)
    protected long revisionNo;
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
     * Gets the value of the hubToHubAtcList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hubToHubAtcList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHubToHubAtcList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HubToHubAtcData }
     * 
     * 
     */
    public List<HubToHubAtcData> getHubToHubAtcList() {
        if (hubToHubAtcList == null) {
            hubToHubAtcList = new ArrayList<HubToHubAtcData>();
        }
        return this.hubToHubAtcList;
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
