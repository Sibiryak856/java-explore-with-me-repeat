package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.StatCreateDto;
import ru.practicum.StatDto;
import ru.practicum.model.StatData;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.practicum.model.StatData.DATE_FORMAT;

@Transactional
@SpringBootTest(
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StatServiceImplIntegrationTest {

    private final StatServiceImpl service;

    private StatCreateDto dto = StatCreateDto.builder()
            .appName("app")
            .uri("uri")
            .ip("ip")
            .created(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
            .build();

    @Test
    void save() {
        StatData statData = service.save(dto);

        assertThat(statData).isNotNull();
        assertTrue(statData.getId() instanceof Long);
        assertThat(statData.getAppName()).isEqualTo(dto.getAppName());
        assertThat(statData.getUri()).isEqualTo(dto.getUri());
        assertThat(statData.getIp()).isEqualTo(dto.getIp());
        assertThat(statData.getCreated())
                .isEqualTo(LocalDateTime.parse(dto.getCreated(), DateTimeFormatter.ofPattern(DATE_FORMAT)));
    }

    @Test
    void getStat() {
        service.save(dto);

        List<StatDto> result = service.getStat(
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1),
                List.of(dto.getUri()),
                Boolean.TRUE);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

        StatDto statDto = result.get(0);

        assertThat(statDto.getApp()).isEqualTo(dto.getAppName());
        assertThat(statDto.getUri()).isEqualTo(dto.getUri());
        assertThat(statDto.getHits()).isEqualTo(1L);
    }

}
