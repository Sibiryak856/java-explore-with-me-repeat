package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.StatClient;

@RestController
@Validated
@Slf4j
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPublicController {

    @Autowired
    StatClient client;

}
