//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.01.05 à 01:46:40 AM GMT+01:00 
//


package com.bankati.cmi.account;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


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
 *         &lt;element name="rechargeRequest" type="{http://www.bankati.com/cmi/account}RechargeRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountRechargeRequest", propOrder = {
        "rechargeRequest"
})
@XmlRootElement(name = "AccountRechargeRequest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRechargeRequest {

    @XmlElement(required = true)
    protected RechargeRequest rechargeRequest;

    /**
     * Obtient la valeur de la propriété rechargeRequest.
     *
     * @return
     *     possible object is
     *     {@link RechargeRequest }
     *
     */
    public RechargeRequest getRechargeRequest() {
        return rechargeRequest;
    }

    /**
     * Définit la valeur de la propriété rechargeRequest.
     *
     * @param value
     *     allowed object is
     *     {@link RechargeRequest }
     *
     */
    public void setRechargeRequest(RechargeRequest value) {
        this.rechargeRequest = value;
    }

}
