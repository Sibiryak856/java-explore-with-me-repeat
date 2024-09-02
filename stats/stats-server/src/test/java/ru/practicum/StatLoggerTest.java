package ru.practicum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.controller.StatController;

@SpringBootTest
public class StatLoggerTest {

    @Autowired
    private StatController controller;

    @Test
    public void testStatLogger() {
        controller.save(StatCreateDto.builder()
                .appName("AppName")
                .uri("Uri")
                .ip("ip")
                .created("2024-09-01 10:11:12")
                .build());
    }
}
