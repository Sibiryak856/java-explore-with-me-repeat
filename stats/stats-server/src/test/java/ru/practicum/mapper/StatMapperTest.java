package ru.practicum.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.practicum.StatCreateDto;
import ru.practicum.model.StatData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.practicum.model.StatData.DATE_FORMAT;

class StatMapperTest {

    private StatMapper mapper = Mappers.getMapper(StatMapper.class);

    @Test
    void toStatData() {
        StatCreateDto dto = StatCreateDto.builder()
                .appName("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.1")
                .created(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .build();

        StatData statData = mapper.toStatData(dto);

        assertThat(statData).isNotNull();
        assertThat(statData.getAppName()).isEqualTo(dto.getAppName());
        assertThat(statData.getUri()).isEqualTo(dto.getUri());
        assertThat(statData.getIp()).isEqualTo(dto.getIp());
        assertThat(statData.getCreated())
                .isEqualTo(LocalDateTime.parse(dto.getCreated(), DateTimeFormatter.ofPattern(DATE_FORMAT)));
    }
}