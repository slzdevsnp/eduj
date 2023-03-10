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
 *         &lt;element name="ProdList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Prod" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ContractList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Contract" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="action" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quoteDeleteDirectionUnionType" />
 *                                               &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
 *                                               &lt;anyAttribute processContents='lax'/>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;anyAttribute processContents='lax'/>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/choice>
 *                           &lt;attribute name="prodName" use="required" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
 *                           &lt;anyAttribute processContents='lax'/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="clOrdrId" type="{http://www.deutsche-boerse.com/m7/v6}clOrdrIdType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="acctId" type="{http://www.deutsche-boerse.com/m7/v6}acctIdType" />
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
    "prodList",
    "clOrdrId"
})
@XmlRootElement(name = "DeleteQuotesReq")
public class DeleteQuotesReq {

    @XmlElement(name = "StandardHeader", required = true)
    protected StandardHeaderType standardHeader;
    @XmlElement(name = "ProdList", required = true)
    protected DeleteQuotesReq.ProdList prodList;
    protected List<String> clOrdrId;
    @XmlAttribute(name = "acctId")
    protected String acctId;
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
     * Gets the value of the prodList property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteQuotesReq.ProdList }
     *     
     */
    public DeleteQuotesReq.ProdList getProdList() {
        return prodList;
    }

    /**
     * Sets the value of the prodList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteQuotesReq.ProdList }
     *     
     */
    public void setProdList(DeleteQuotesReq.ProdList value) {
        this.prodList = value;
    }

    /**
     * Gets the value of the clOrdrId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clOrdrId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClOrdrId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClOrdrId() {
        if (clOrdrId == null) {
            clOrdrId = new ArrayList<String>();
        }
        return this.clOrdrId;
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
     *         &lt;element name="Prod" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ContractList">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Contract" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="action" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quoteDeleteDirectionUnionType" />
     *                                     &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
     *                                     &lt;anyAttribute processContents='lax'/>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;anyAttribute processContents='lax'/>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/choice>
     *                 &lt;attribute name="prodName" use="required" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
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
        "prod"
    })
    public static class ProdList {

        @XmlElement(name = "Prod", required = true)
        protected List<DeleteQuotesReq.ProdList.Prod> prod;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the prod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the prod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProd().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DeleteQuotesReq.ProdList.Prod }
         * 
         * 
         */
        public List<DeleteQuotesReq.ProdList.Prod> getProd() {
            if (prod == null) {
                prod = new ArrayList<DeleteQuotesReq.ProdList.Prod>();
            }
            return this.prod;
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


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ContractList">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Contract" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="action" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quoteDeleteDirectionUnionType" />
         *                           &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
         *                           &lt;anyAttribute processContents='lax'/>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;anyAttribute processContents='lax'/>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/choice>
         *       &lt;attribute name="prodName" use="required" type="{http://www.deutsche-boerse.com/m7/v6}stringType255" />
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
            "action",
            "contractList"
        })
        public static class Prod {

            protected String action;
            @XmlElement(name = "ContractList")
            protected DeleteQuotesReq.ProdList.Prod.ContractList contractList;
            @XmlAttribute(name = "prodName", required = true)
            protected String prodName;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
             * Gets the value of the contractList property.
             * 
             * @return
             *     possible object is
             *     {@link DeleteQuotesReq.ProdList.Prod.ContractList }
             *     
             */
            public DeleteQuotesReq.ProdList.Prod.ContractList getContractList() {
                return contractList;
            }

            /**
             * Sets the value of the contractList property.
             * 
             * @param value
             *     allowed object is
             *     {@link DeleteQuotesReq.ProdList.Prod.ContractList }
             *     
             */
            public void setContractList(DeleteQuotesReq.ProdList.Prod.ContractList value) {
                this.contractList = value;
            }

            /**
             * Gets the value of the prodName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProdName() {
                return prodName;
            }

            /**
             * Sets the value of the prodName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProdName(String value) {
                this.prodName = value;
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
             *         &lt;element name="Contract" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="action" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quoteDeleteDirectionUnionType" />
             *                 &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
             *                 &lt;anyAttribute processContents='lax'/>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
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
                "contract"
            })
            public static class ContractList {

                @XmlElement(name = "Contract", required = true)
                protected List<DeleteQuotesReq.ProdList.Prod.ContractList.Contract> contract;
                @XmlAnyAttribute
                private Map<QName, String> otherAttributes = new HashMap<QName, String>();

                /**
                 * Gets the value of the contract property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the contract property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getContract().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link DeleteQuotesReq.ProdList.Prod.ContractList.Contract }
                 * 
                 * 
                 */
                public List<DeleteQuotesReq.ProdList.Prod.ContractList.Contract> getContract() {
                    if (contract == null) {
                        contract = new ArrayList<DeleteQuotesReq.ProdList.Prod.ContractList.Contract>();
                    }
                    return this.contract;
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


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="action" use="required" type="{http://www.deutsche-boerse.com/m7/v6}quoteDeleteDirectionUnionType" />
                 *       &lt;attribute name="contractId" use="required" type="{http://www.deutsche-boerse.com/m7/v6}longType" />
                 *       &lt;anyAttribute processContents='lax'/>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Contract {

                    @XmlAttribute(name = "action", required = true)
                    protected String action;
                    @XmlAttribute(name = "contractId", required = true)
                    protected long contractId;
                    @XmlAnyAttribute
                    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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

            }

        }

    }

}
