package com.example.church_management_system.controller;

import com.example.church_management_system.config.JwtAuthenticationFilter;
import com.example.church_management_system.controller.event.EventController;
import com.example.church_management_system.dto.event.EventDto;
import com.example.church_management_system.service.CustomUserDetailsService;
import com.example.church_management_system.service.event.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private EventDto eventDto;

    @BeforeEach
    void setUp() {
        eventDto = EventDto.builder()
                .id(1L)
                .title("Choir Practice")
                .location("Hall B")
                .description("Weekly practice")
                .date(LocalDate.now())
                .time(LocalTime.of(17, 0))
                .build();
    }

    @Test
    @WithMockUser
    void getAllEvents_ShouldReturnOk() throws Exception {
        when(eventService.findAllEvents()).thenReturn(List.of(eventDto));

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Choir Practice"));
    }

    @Test
    @WithMockUser
    void getEventById_ShouldReturnEvent() throws Exception {
        when(eventService.getEventById(1L)).thenReturn(eventDto);

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Choir Practice"));
    }

    @Test
    @WithMockUser
    void createEvent_ShouldReturnCreated() throws Exception {
        when(eventService.createEvent(any(EventDto.class))).thenReturn(eventDto);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Choir Practice"));
    }
}
