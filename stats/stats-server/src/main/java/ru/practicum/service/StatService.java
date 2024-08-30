package ru.practicum.service;

import ru.practicum.StatCreateDto;
import ru.practicum.StatDto;
import ru.practicum.model.StatData;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {

    StatData save (StatCreateDto dto);

    List<StatDto> getStat(LocalDateTime start,
                          LocalDateTime end,
                          List<String> uris,
                          Boolean unique);

}
