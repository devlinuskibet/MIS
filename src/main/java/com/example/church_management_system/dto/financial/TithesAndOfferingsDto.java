package com.example.church_management_system.dto.financial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TithesAndOfferingsDto {
    private Long id;
    private BigDecimal amount;
    private Date dateOfContribution;
}
