package com.example.church_management_system.service.impl.sermon;

import com.example.church_management_system.dto.sermon.SermonDto;
import com.example.church_management_system.model.sermon.Sermon;
import com.example.church_management_system.repository.sermon.SermonRepository;
import com.example.church_management_system.service.sermon.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SermonServiceImpl implements SermonService {

    private final SermonRepository sermonRepository;

    @Autowired
    public SermonServiceImpl(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SermonDto> findAllSermons() {
        return sermonRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SermonDto getSermonById(Long id) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));
        return toDto(sermon);
    }

    @Override
    public SermonDto createSermon(SermonDto sermonDto) {
        Sermon sermon = toEntity(sermonDto);
        Sermon savedSermon = sermonRepository.save(sermon);
        return toDto(savedSermon);
    }

    @Override
    public SermonDto updateSermon(Long id, SermonDto sermonDto) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));

        sermon.setTitle(sermonDto.getTitle());
        sermon.setSpeaker(sermonDto.getSpeaker());
        if (sermonDto.getDate() != null) {
            sermon.setDate(sermonDto.getDate());
        }
        sermon.setUrl(sermonDto.getUrl());
        sermon.setDescription(sermonDto.getDescription());

        Sermon updatedSermon = sermonRepository.save(sermon);
        return toDto(updatedSermon);
    }

    @Override
    public void deleteSermon(Long id) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));
        sermonRepository.delete(sermon);
    }

    private SermonDto toDto(Sermon sermon) {
        return SermonDto.builder()
                .id(sermon.getId())
                .title(sermon.getTitle())
                .speaker(sermon.getSpeaker())
                .date(sermon.getDate())
                .url(sermon.getUrl())
                .description(sermon.getDescription())
                .build();
    }

    private Sermon toEntity(SermonDto dto) {
        return Sermon.builder()
                .title(dto.getTitle())
                .speaker(dto.getSpeaker())
                .date(dto.getDate())
                .url(dto.getUrl())
                .description(dto.getDescription())
                .build();
    }
}
