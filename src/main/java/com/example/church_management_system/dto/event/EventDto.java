package com.example.church_management_system.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private String title;
    private String location;
    private String description;
    private LocalDate date;
    private LocalTime time;
}
