package ru.practicum.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.StatDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class StatDtoTest {

    @Autowired
    private JacksonTester<StatDto> json;

    @Test
    void StatDtoToJsonTest() throws IOException {
        StatDto dto = StatDto.builder()
                .app("appName")
                .uri("uri")
                .hits(3L)
                .build();

        JsonContent<StatDto> result = json.write(dto);

        assertThat(result).extractingJsonPathStringValue("$.app").isEqualTo(dto.getApp());
        assertThat(result).extractingJsonPathStringValue("$.uri").isEqualTo(dto.getUri());
        assertThat(result).extractingJsonPathNumberValue("$.hits").isEqualTo(dto.getHits().intValue());
    }

}
