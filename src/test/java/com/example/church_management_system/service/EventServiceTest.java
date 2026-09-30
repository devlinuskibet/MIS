package com.example.church_management_system.service;

import com.example.church_management_system.dto.event.EventDto;
import com.example.church_management_system.model.Event;
import com.example.church_management_system.repository.event.EventRepository;
import com.example.church_management_system.service.impl.event.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;
    private EventDto eventDto;

    @BeforeEach
    void setUp() {
        event = Event.builder()
                .id(1L)
                .title("Sunday Service")
                .location("Main Auditorium")
                .description("Worship & Word")
                .date(LocalDate.now())
                .time(LocalTime.of(9, 0))
                .build();

        eventDto = EventDto.builder()
                .title("Sunday Service")
                .location("Main Auditorium")
                .description("Worship & Word")
                .time(LocalTime.of(9, 0))
                .build();
    }

    @Test
    void createEvent_ShouldSaveAndReturnDto() {
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventDto created = eventService.createEvent(eventDto);

        assertNotNull(created);
        assertEquals("Sunday Service", created.getTitle());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void getEventById_WhenExists_ShouldReturnDto() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        EventDto found = eventService.getEventById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Sunday Service", found.getTitle());
    }

    @Test
    void findAllEvents_ShouldReturnList() {
        when(eventRepository.findAll()).thenReturn(List.of(event));

        List<EventDto> events = eventService.findAllEvents();

        assertNotNull(events);
        assertEquals(1, events.size());
    }
}
