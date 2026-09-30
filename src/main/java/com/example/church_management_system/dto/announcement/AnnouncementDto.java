package com.example.church_management_system.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {
    private Long id;
    private String announcementType;
    private String title;
    private String message;
    private LocalDateTime dateCreated;
    private LocalDate effectiveDate;
    private LocalDate effectiveUntil;
    private String targetAudience;
}
