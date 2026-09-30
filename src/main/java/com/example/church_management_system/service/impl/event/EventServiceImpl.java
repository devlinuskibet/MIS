package com.example.church_management_system.service.impl.event;

import com.example.church_management_system.dto.event.EventDto;
import com.example.church_management_system.model.Event;
import com.example.church_management_system.repository.event.EventRepository;
import com.example.church_management_system.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = Event.builder()
                .title(eventDto.getTitle())
                .location(eventDto.getLocation())
                .description(eventDto.getDescription())
                .time(eventDto.getTime())
                .build();

        Event savedEvent = eventRepository.save(event);
        return toDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        if (eventDto.getId() == null) {
            throw new IllegalArgumentException("Event ID is required for update");
        }

        Event event = eventRepository.findById(eventDto.getId())
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventDto.getId()));

        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        if (eventDto.getTime() != null) {
            event.setTime(eventDto.getTime());
        }

        Event updatedEvent = eventRepository.save(event);
        return toDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID is required for deletion");
        }
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID is required");
        }
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
        return toDto(event);
    }

    private EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .date(event.getDate())
                .time(event.getTime())
                .location(event.getLocation())
                .description(event.getDescription())
                .build();
    }
}
