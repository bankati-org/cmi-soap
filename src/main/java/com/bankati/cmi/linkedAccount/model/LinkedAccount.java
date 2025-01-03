package com.bankati.cmi.linkedAccount.model;


import com.bankati.cmi.linkedAccount.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "linked_account")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class LinkedAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(columnDefinition = "TEXT")
    private String accountDetails;

    @Column(nullable = false)
    private boolean isDefault = false;

    @Column(nullable = false)
    private boolean isVerified = false;

    public LinkedAccount() {
    }
}