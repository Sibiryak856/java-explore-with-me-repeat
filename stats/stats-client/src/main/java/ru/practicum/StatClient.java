package ru.practicum;

import java.util.List;

public interface StatClient {

    void postStat(StatCreateDto createDto);

    List<StatDto> getStats(String start, String end, List<String> uris, Boolean unique);

}
