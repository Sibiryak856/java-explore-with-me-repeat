package ru.practicum.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.practicum.user.model.User;
import user.UserCreateDto;
import user.UserDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserCreateDto userCreateDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}
