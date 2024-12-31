package com.bankati.cmi.Transaction.dto;

import com.bankati.cmi.Transaction.enums.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;
import org.jetbrains.annotations.NotNull;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionDto", propOrder = {
        "externalTransactionId",
        "senderAccountId",
        "recipientAccountId",
        "amount",
        "transactionDate",
        "status",
        "description",
        "transactionType",
        "currency"
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String externalTransactionId ;
    
    private String senderAccountId;

    @NotNull
    private String recipientAccountId;

    @NotNull
    private Double amount;

    @NotNull
    private String transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;

    @Enumerated(EnumType.STRING)
    private String transactionType;

    private String currency;
    
}
