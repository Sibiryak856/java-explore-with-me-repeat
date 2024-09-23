package ru.practicum.user.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import user.UserShortDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserShortDtoTest {

    @Autowired
    private JacksonTester<UserShortDto> json;

    @Test
    void testUserShortDto() throws IOException {
        UserShortDto user = UserShortDto.builder()
                .id(1L)
                .name("Name")
                .build();

        JsonContent<UserShortDto> result = json.write(user);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(user.getId().intValue());
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo(user.getName());
    }
}