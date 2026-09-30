package com.example.church_management_system.service;

import com.example.church_management_system.dto.MemberRegistrationDto;
import com.example.church_management_system.model.MemberRegistration;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.service.impl.MemberRegistrationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberRegistrationServiceTest {

    @Mock
    private MemberRegistrationRepository memberRegistrationRepository;

    @InjectMocks
    private MemberRegistrationImpl memberRegistrationService;

    private MemberRegistration member;
    private MemberRegistrationDto memberDto;

    @BeforeEach
    void setUp() {
        member = MemberRegistration.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .phone("1234567890")
                .dob(LocalDate.of(1990, 1, 1))
                .address("123 Street")
                .dateJoined(LocalDate.now())
                .build();

        memberDto = MemberRegistrationDto.builder()
                .fullName("John Doe")
                .email("john@example.com")
                .phone("1234567890")
                .dob(LocalDate.of(1990, 1, 1))
                .address("123 Street")
                .build();
    }

    @Test
    void findAllMemberRegistrations_ShouldReturnList() {
        when(memberRegistrationRepository.findAll()).thenReturn(List.of(member));

        List<MemberRegistrationDto> result = memberRegistrationService.findAllMemberRegistrations();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        verify(memberRegistrationRepository, times(1)).findAll();
    }

    @Test
    void createMember_ShouldSaveAndReturnDto() {
        when(memberRegistrationRepository.save(any(MemberRegistration.class))).thenReturn(member);

        MemberRegistrationDto created = memberRegistrationService.createMember(memberDto);

        assertNotNull(created);
        assertEquals("John Doe", created.getFullName());
        verify(memberRegistrationRepository, times(1)).save(any(MemberRegistration.class));
    }

    @Test
    void deleteMember_WhenExists_ShouldDelete() {
        when(memberRegistrationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(memberRegistrationRepository).deleteById(1L);

        assertDoesNotThrow(() -> memberRegistrationService.deleteMember(1L));
        verify(memberRegistrationRepository, times(1)).deleteById(1L);
    }
}
