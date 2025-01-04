//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.01.04 à 03:09:02 AM GMT+01:00 
//


package com.bankati.cmi.account;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bankati.cmi.account package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bankati.cmi.account
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateCreateAccountRequest }
     * 
     */
    public ValidateCreateAccountRequest createValidateCreateAccountRequest() {
        return new ValidateCreateAccountRequest();
    }

    /**
     * Create an instance of {@link CreateAccountRequest }
     * 
     */
    public CreateAccountRequest createCreateAccountRequest() {
        return new CreateAccountRequest();
    }

    /**
     * Create an instance of {@link ValidateCreateAccountResponse }
     * 
     */
    public ValidateCreateAccountResponse createValidateCreateAccountResponse() {
        return new ValidateCreateAccountResponse();
    }

    /**
     * Create an instance of {@link CreateAccountResponse }
     * 
     */
    public CreateAccountResponse createCreateAccountResponse() {
        return new CreateAccountResponse();
    }

    /**
     * Create an instance of {@link GetAccountDetailsRequest }
     * 
     */
    public GetAccountDetailsRequest createGetAccountDetailsRequest() {
        return new GetAccountDetailsRequest();
    }

    /**
     * Create an instance of {@link GetAccountDetailsResponse }
     * 
     */
    public GetAccountDetailsResponse createGetAccountDetailsResponse() {
        return new GetAccountDetailsResponse();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

}
