package com.example.church_management_system.service.impl.financial;

import com.example.church_management_system.dto.financial.MemberContributionDto;
import com.example.church_management_system.model.MemberRegistration;
import com.example.church_management_system.model.financial.MemberContribution;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.repository.financial.MemberContributionRepository;
import com.example.church_management_system.service.financial.MemberContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberContributionImpl implements MemberContributionService {

    private final MemberContributionRepository memberContributionRepository;
    private final MemberRegistrationRepository memberRegistrationRepository;

    @Autowired
    public MemberContributionImpl(MemberContributionRepository memberContributionRepository,
                                  MemberRegistrationRepository memberRegistrationRepository) {
        this.memberContributionRepository = memberContributionRepository;
        this.memberRegistrationRepository = memberRegistrationRepository;
    }

    @Override
    public MemberContribution saveContribution(MemberContributionDto contributionDto) {
        MemberRegistration member = memberRegistrationRepository.findById(contributionDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + contributionDto.getMemberId()));

        MemberContribution contribution = MemberContribution.builder()
                .member(member)
                .memberName(contributionDto.getMemberName())
                .contributionType(contributionDto.getContributionType())
                .amount(contributionDto.getAmount())
                .modeOfPayment(contributionDto.getModeOfPayment())
                .dateOfContribution(contributionDto.getDateOfContribution())
                .build();

        return memberContributionRepository.save(contribution);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberContribution> getContributionsByMemberId(Long memberId) {
        return memberContributionRepository.findByMember_Id(memberId);
    }

    @Override
    public MemberContribution updateContribution(Long id, MemberContribution updatedContribution) {
        Optional<MemberContribution> existingContribution = memberContributionRepository.findById(id);
        if (existingContribution.isPresent()) {
            MemberContribution contribution = existingContribution.get();
            contribution.setMemberName(updatedContribution.getMemberName());
            contribution.setContributionType(updatedContribution.getContributionType());
            contribution.setAmount(updatedContribution.getAmount());
            contribution.setDateOfContribution(updatedContribution.getDateOfContribution());
            contribution.setModeOfPayment(updatedContribution.getModeOfPayment());
            return memberContributionRepository.save(contribution);
        } else {
            throw new RuntimeException("Contribution not found with ID: " + id);
        }
    }

    @Override
    public void deleteContribution(Long id) {
        memberContributionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberContribution getContributionById(Long id) {
        return memberContributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribution not found with ID: " + id));
    }
}
