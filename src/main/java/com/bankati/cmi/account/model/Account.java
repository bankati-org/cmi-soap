package com.bankati.cmi.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,updatable = false,length = 16)
    @NotBlank(message = "Account number cannot be blank")
    @Size(max = 16,min = 16,message = "accountNumber should have exactly 16 digits")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    private Double balance;

    @NotBlank(message = "Currency cannot be blank")
    @Size(max = 3, message = "Currency code should be a maximum of 3 characters")
    private String currency;

    @NotBlank(message = "Owner ID cannot be blank")
    @Column(nullable = false,unique = true)
    private String ownerId; // ID of the user in User Management

    @Column(unique = true)
    @NotBlank(message = "CIN cannot be blank")
    private String ownerCin;

}
