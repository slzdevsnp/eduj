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


/**
 * <p>Java class for ClgHseCodeAcctType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClgHseCodeAcctType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.deutsche-boerse.com/m7/v6}ClgHseCodeType">
 *       &lt;attribute name="clgAcctId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}intType" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClgHseCodeAcctType")
public class ClgHseCodeAcctType
    extends ClgHseCodeType
{

    @XmlAttribute(name = "clgAcctId", required = true)
    protected int clgAcctId;

    /**
     * Gets the value of the clgAcctId property.
     * 
     */
    public int getClgAcctId() {
        return clgAcctId;
    }

    /**
     * Sets the value of the clgAcctId property.
     * 
     */
    public void setClgAcctId(int value) {
        this.clgAcctId = value;
    }

}
