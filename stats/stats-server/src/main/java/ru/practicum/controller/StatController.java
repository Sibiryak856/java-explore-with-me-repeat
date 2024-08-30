package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.service.StatService;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class StatController {

    private final StatService service;
}
