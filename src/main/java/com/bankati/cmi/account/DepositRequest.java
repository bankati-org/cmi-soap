//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.01.06 à 08:38:56 PM GMT+01:00 
//


package com.bankati.cmi.account;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DepositRequest complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DepositRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositRequest", propOrder = {
    "userId",
    "balance"
})
public class DepositRequest {

    protected String ownerCin;
    protected double balance;

    /**
     * Obtient la valeur de la propriété userId.
     * 
     */
    public String getOwnerCin() {
        return ownerCin;
    }

    /**
     * Définit la valeur de la propriété userId.
     * 
     */
    public void setOwnerCin(String value) {
        this.ownerCin = value;
    }

    /**
     * Obtient la valeur de la propriété balance.
     * 
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Définit la valeur de la propriété balance.
     * 
     */
    public void setBalance(double value) {
        this.balance = value;
    }

}
