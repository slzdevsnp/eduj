//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.01 at 01:25:50 PM CEST 
//


package org.slzdevsnp.binding.tmtrading;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListExecInstType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ListExecInstType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LNKD"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="VALID"/>
 *     &lt;enumeration value="IMPL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ListExecInstType")
@XmlEnum
public enum ListExecInstType {

    LNKD,
    NONE,
    VALID,
    IMPL;

    public String value() {
        return name();
    }

    public static ListExecInstType fromValue(String v) {
        return valueOf(v);
    }

}
