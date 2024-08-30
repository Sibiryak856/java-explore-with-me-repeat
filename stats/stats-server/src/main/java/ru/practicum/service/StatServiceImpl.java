package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.StatCreateDto;
import ru.practicum.StatDto;
import ru.practicum.mapper.StatMapper;
import ru.practicum.model.StatData;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository repository;
    private final StatMapper mapper;

    @Transactional
    @Override
    public StatData save(StatCreateDto dto) {
        return repository.save(mapper.toStatData(dto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<StatDto> getStat(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end time");
        }
        if (unique) {
            return repository.findAllUniqueHitByTimeBetween(start, end, uris);
        }
        return repository.findAllByTimeBetween(start, end, uris);
    }
}
