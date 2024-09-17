package ru.practicum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.practicum.StatCreateDto;
import ru.practicum.service.StatServiceImpl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.practicum.model.StatData.DATE_FORMAT;

@WebMvcTest(controllers = StatController.class)
class StatControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StatServiceImpl service;

    @Autowired
    private MockMvc mvc;

    private StatCreateDto dto;

    @BeforeEach
    void setUp() {
        dto = StatCreateDto.builder()
                .appName("app")
                .uri("uri")
                .ip("ip")
                .created(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .build();
    }

    @Test
    void save_whenDataIsValid_thenReturnCreated() throws Exception {
        mvc.perform(post("/hit")
                .content(String.valueOf(mapper.writeValueAsString(dto)))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service).save(any(StatCreateDto.class));
        verify(service, atMostOnce()).save(any(StatCreateDto.class));
    }

    @Test
    void save_whenDataIsNotValid_thenReturnBadRequest() throws Exception {
        dto.setAppName(" ");

        mvc.perform(post("/hit")
                .content(String.valueOf(mapper.writeValueAsString(dto)))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(result -> assertEquals("appName=must not be blank",
                        result.getResolvedException().getMessage()));

        verify(service, never()).save(any(StatCreateDto.class));
    }

    @Test
    void getHits() {
    }
}