//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.01.05 à 01:46:40 AM GMT+01:00 
//


package com.bankati.cmi.account;

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
 *         &lt;element name="createAccountResponse" type="{http://www.bankati.com/cmi/account}CreateAccountResponse"/&gt;
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
    "createAccountResponse"
})
@XmlRootElement(name = "ValidateCreateAccountResponse")
public class ValidateCreateAccountResponse {

    @XmlElement(required = true)
    protected CreateAccountResponse createAccountResponse;

    /**
     * Obtient la valeur de la propriété createAccountResponse.
     * 
     * @return
     *     possible object is
     *     {@link CreateAccountResponse }
     *     
     */
    public CreateAccountResponse getCreateAccountResponse() {
        return createAccountResponse;
    }

    /**
     * Définit la valeur de la propriété createAccountResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateAccountResponse }
     *     
     */
    public void setCreateAccountResponse(CreateAccountResponse value) {
        this.createAccountResponse = value;
    }

}
