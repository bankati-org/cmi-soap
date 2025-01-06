package com.bankati.cmi.account.model;

import com.bankati.cmi.enums.RechargeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RechargeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transaction ID cannot be blank")
    @Column(nullable = false)
    private String transactionId;

    @NotBlank(message = "Account number cannot be blank")
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Recharge type cannot be blank")
    @Column(nullable = false)
    private RechargeType rechargeType;

    @NotBlank(message = "userId cannot be blank")
    @Column(nullable = false)
    private String userId;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @NotBlank(message = "Status cannot be blank")
    @Column(nullable = false)
    private String status;
}
