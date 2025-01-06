package com.bankati.cmi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateWalletRequest {

    @NotNull
    private Long userId;

    @NotNull
    @Size(min = 3, max = 3)
    private String defaultCurrency;
}