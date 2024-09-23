package ru.practicum.user.service;

import org.springframework.data.domain.Pageable;
import user.UserCreateDto;
import user.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserCreateDto userCreateDto);

    List<UserDto> getAll(List<Long> ids, Pageable pageable);

    void delete(long id);

}
