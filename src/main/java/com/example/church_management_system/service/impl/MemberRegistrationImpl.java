package com.example.church_management_system.service.impl;

import com.example.church_management_system.dto.MemberRegistrationDto;
import com.example.church_management_system.model.MemberRegistration;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.service.MemberRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberRegistrationImpl implements MemberRegistrationService {

    private final MemberRegistrationRepository memberRegistrationRepository;

    @Autowired
    public MemberRegistrationImpl(MemberRegistrationRepository memberRegistrationRepository, PasswordEncoder passwordEncoder) {
        this.memberRegistrationRepository = memberRegistrationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberRegistrationDto> findAllMemberRegistrations() {
        return memberRegistrationRepository.findAll()
                .stream()
                .map(this::mapToMemberRegistrationDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberRegistrationDto mapToMemberRegistrationDto(MemberRegistration memberRegistration) {
        return MemberRegistrationDto.builder()
                .id(memberRegistration.getId())
                .fullName(memberRegistration.getFullName())
                .email(memberRegistration.getEmail())
                .phone(memberRegistration.getPhone())
                .dob(memberRegistration.getDob())
                .address(memberRegistration.getAddress())
                .dateJoined(memberRegistration.getDateJoined())
                .build();
    }

    @Override
    public MemberRegistrationDto createMember(MemberRegistrationDto memberDto) {
        MemberRegistration member = MemberRegistration.builder()
                .fullName(memberDto.getFullName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .dob(memberDto.getDob())
                .address(memberDto.getAddress())
                .build();

        MemberRegistration savedMember = memberRegistrationRepository.save(member);
        return mapToMemberRegistrationDto(savedMember);
    }

    @Override
    public MemberRegistrationDto updateMember(Long id, MemberRegistrationDto memberDto) {
        Optional<MemberRegistration> optionalMember = memberRegistrationRepository.findById(id);
        if (optionalMember.isPresent()) {
            MemberRegistration member = optionalMember.get();
            member.setFullName(memberDto.getFullName());
            member.setEmail(memberDto.getEmail());
            member.setPhone(memberDto.getPhone());
            member.setDob(memberDto.getDob());
            member.setAddress(memberDto.getAddress());
            member = memberRegistrationRepository.save(member);
            return mapToMemberRegistrationDto(member);
        } else {
            throw new RuntimeException("Member not found with ID: " + id);
        }
    }

    @Override
    public void deleteMember(Long id) {
        if (memberRegistrationRepository.existsById(id)) {
            memberRegistrationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Member not found with ID: " + id);
        }
    }
}
