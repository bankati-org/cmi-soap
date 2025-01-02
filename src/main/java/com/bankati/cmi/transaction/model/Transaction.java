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
@XmlRootElement(name = "Transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalTransactionId;

    @NotNull
    @Column(name = "sender_account_id")
    private String senderAccountId;

    @NotNull
    @Column(name = "recipient_account_id")
    private String recipientAccountId;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_type")
    private String transactionType;

    private String currency;

    public Transaction() {

    }
}

