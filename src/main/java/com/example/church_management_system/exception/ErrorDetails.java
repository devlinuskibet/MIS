package com.example.church_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private boolean success;
}
