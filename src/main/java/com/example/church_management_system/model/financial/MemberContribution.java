package com.example.church_management_system.model.financial;

import com.example.church_management_system.model.MemberRegistration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member_contribution")
public class MemberContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberRegistration member;

    private String memberName;
    private String contributionType;
    private BigDecimal amount;
    private String modeOfPayment;
    private Date dateOfContribution;
}
