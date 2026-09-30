package com.example.church_management_system.dto.sermon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SermonDto {
    private Long id;
    private String title;
    private String speaker;
    private LocalDate date;
    private String url;
    private String description;
}
