package com.bankati.cmi.transaction.model;

import com.bankati.cmi.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long accountId;

    @Column(name = "transaction_type")
    private String transactionType;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "sender_account_id")
    private String senderAccountNumber;

    @NotNull
    @Column(name = "recipient_account_id")
    private String recipientAccountNumber;

    public Transaction() {

    }
}

