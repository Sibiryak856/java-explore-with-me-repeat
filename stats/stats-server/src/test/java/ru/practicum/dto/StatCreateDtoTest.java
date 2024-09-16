package ru.practicum.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.StatCreateDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.practicum.model.StatData.DATE_FORMAT;

@JsonTest
public class StatCreateDtoTest {

    @Autowired
    private JacksonTester<StatCreateDto> json;

    @Test
    void testStatCreateDto() throws IOException {
        StatCreateDto dto = StatCreateDto.builder()
                .appName("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.1")
                .created(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .build();

        JsonContent<StatCreateDto> result = json.write(dto);

        assertThat(result).extractingJsonPathStringValue("$.app").isEqualTo(dto.getAppName());
        assertThat(result).extractingJsonPathStringValue("$.uri").isEqualTo(dto.getUri());
        assertThat(result).extractingJsonPathStringValue("$.ip").isEqualTo(dto.getIp());
        assertThat(result).extractingJsonPathStringValue("$.timestamp").isEqualTo(dto.getCreated());
    }
}
