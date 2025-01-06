//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.01.06 à 04:01:25 PM GMT+01:00 
//


package com.bankati.cmi.payment;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fault" type="{http://bankati.com/cmi/payment}ValidationFault"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fault"
})
@XmlRootElement(name = "ValidationException")
public class ValidationException {

    @XmlElement(required = true)
    protected ValidationFault fault;

    /**
     * Obtient la valeur de la propriété fault.
     * 
     * @return
     *     possible object is
     *     {@link ValidationFault }
     *     
     */
    public ValidationFault getFault() {
        return fault;
    }

    /**
     * Définit la valeur de la propriété fault.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationFault }
     *     
     */
    public void setFault(ValidationFault value) {
        this.fault = value;
    }

}
