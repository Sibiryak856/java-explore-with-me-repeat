package ru.practicum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import ru.practicum.StatCreateDto;
import ru.practicum.service.StatServiceImpl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

        verify(service, never()).save(any(StatCreateDto.class));
    }

    @Test
    void getHits_whenRequestIsValid_thenStatusIsOk() throws Exception {
        long userId = 1L;

        when(service.getStat(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList(),
                anyBoolean()))
                .thenReturn(new ArrayList<>());

        String result = mvc.perform(get("/stats", userId)
                        .param("start", "2020-05-05 00:00:00")
                        .param("end", "2035-05-05 00:00:00")
                        .param("uris", "/events")
                        .param("unique", "false")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(mapper.writeValueAsString(new ArrayList<>()));
        verify(service).getStat(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList(),
                anyBoolean());
    }

    @Test
    void getHits_whenParamIsNotPresent_thenBadRequest() throws Exception {
        long userId = 1L;

        mvc.perform(get("/stats", userId)
                        .param("end", "2035-05-05 00:00:00")
                        .param("uris", "/events")
                        .param("unique", "false")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof MissingServletRequestParameterException));

        verify(service, never()).getStat(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList(),
                anyBoolean());
    }
}