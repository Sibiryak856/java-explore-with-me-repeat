package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StatClientImpl implements StatClient {

    @Override
    public void postStat(StatCreateDto createDto) {

    }

    @Override
    public List<StatDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        return null;
    }
}
