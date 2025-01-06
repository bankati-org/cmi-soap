package com.bankati.cmi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class depositRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Double balance;
}
