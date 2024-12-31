package com.bankati.cmi.Account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    private Double balance;

    @NotBlank(message = "Currency cannot be blank")
    @Size(max = 3, message = "Currency code should be a maximum of 3 characters")
    private String currency;

    @NotBlank(message = "Owner ID cannot be blank")
    private String ownerId; // ID of the user in User Management

    @Column(unique = true)
    @NotBlank(message = "CIN cannot be blank")
    private String ownerCin;

}
