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
public class MemberContributionDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private String contributionType;
    private BigDecimal amount;
    private String modeOfPayment;
    private Date dateOfContribution;
}
