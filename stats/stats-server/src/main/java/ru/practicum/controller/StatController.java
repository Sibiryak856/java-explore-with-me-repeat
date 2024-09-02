package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatCreateDto;
import ru.practicum.StatDto;
import ru.practicum.logger.StatLogger;
import ru.practicum.service.StatService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.model.StatData.DATE_FORMAT;

// TODO logging in annotation

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class StatController {

    private final StatService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hit")
    @StatLogger
    public void save(@RequestBody @Valid StatCreateDto dto) {
        service.save(dto);
    }

    @GetMapping("/stats")
    @StatLogger
    public List<StatDto> getHits(
            @RequestParam("start") @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(value = "unique", required = false, defaultValue = "false") Boolean unique) {
        return service.getStat(start, end, uris, unique);
    }

}
