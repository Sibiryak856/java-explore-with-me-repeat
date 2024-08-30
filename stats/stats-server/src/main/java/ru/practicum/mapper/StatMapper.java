package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.practicum.StatCreateDto;
import ru.practicum.model.StatData;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static ru.practicum.model.StatData.DATE_FORMAT;

@Component
@Mapper(componentModel = SPRING)
public interface StatMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", source = "created", dateFormat = DATE_FORMAT)
    StatData toStatData(StatCreateDto dto);
}
