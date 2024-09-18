package ru.practicum.user.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserDtoTest {

    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    void testUserDto() throws IOException {
        UserDto user = UserDto.builder()
                .id(1L)
                .name("Name")
                .email("name@email.com")
                .build();

        JsonContent<UserDto> result = json.write(user);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(user.getId().intValue());
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo(user.getName());
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo(user.getEmail());
    }
}