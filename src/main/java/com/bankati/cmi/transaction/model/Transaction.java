package com.bankati.cmi.transaction.model;

import com.bankati.cmi.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
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
    private String senderAccountId;

    @NotNull
    @Column(name = "recipient_account_id")
    private String recipientAccountId;

    private String externalTransactionId;

    private Double fees;

    public Transaction() {

    }
}

