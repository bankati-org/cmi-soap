package com.bankati.cmi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    @Builder.Default
    private T data = (T) "No data available";  // Default value for data
    private String status;          // e.g., "success", "error"
    private int statusCode;         // HTTP status code
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}