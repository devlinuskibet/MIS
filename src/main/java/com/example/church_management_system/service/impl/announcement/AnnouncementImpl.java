package com.example.church_management_system.service.impl.announcement;

import com.example.church_management_system.dto.announcement.AnnouncementDto;
import com.example.church_management_system.model.announcement.Announcement;
import com.example.church_management_system.repository.announcement.AnnouncementRepository;
import com.example.church_management_system.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnnouncementImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDto dto) {
        Announcement announcement = Announcement.builder()
                .title(dto.getTitle())
                .message(dto.getMessage())
                .dateCreated(LocalDateTime.now())
                .effectiveDate(dto.getEffectiveDate())
                .effectiveUntil(dto.getEffectiveUntil())
                .targetAudience(dto.getTargetAudience())
                .announcementType(dto.getAnnouncementType())
                .build();

        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return toDto(savedAnnouncement);
    }

    @Override
    @Transactional(readOnly = true)
    public AnnouncementDto getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));
        return toDto(announcement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementDto> getActiveAnnouncements() {
        LocalDate now = LocalDate.now();
        return announcementRepository.findAll().stream()
                .filter(a -> (a.getEffectiveDate() != null && !a.getEffectiveDate().isAfter(now)) &&
                        (a.getEffectiveUntil() == null || !a.getEffectiveUntil().isBefore(now)))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto updateAnnouncement(Long id, AnnouncementDto dto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));

        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setEffectiveDate(dto.getEffectiveDate());
        announcement.setEffectiveUntil(dto.getEffectiveUntil());
        announcement.setTargetAudience(dto.getTargetAudience());
        announcement.setAnnouncementType(dto.getAnnouncementType());

        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return toDto(updatedAnnouncement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));
        announcementRepository.delete(announcement);
    }

    private AnnouncementDto toDto(Announcement announcement) {
        return AnnouncementDto.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .message(announcement.getMessage())
                .dateCreated(announcement.getDateCreated())
                .effectiveDate(announcement.getEffectiveDate())
                .effectiveUntil(announcement.getEffectiveUntil())
                .targetAudience(announcement.getTargetAudience())
                .announcementType(announcement.getAnnouncementType())
                .build();
    }
}
