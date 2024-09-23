package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.service.UserService;
import user.UserCreateDto;
import user.UserDto;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UserAdminController {

    private final UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto saveUser(@RequestBody UserCreateDto userCreateDto) {
        log.info("Request received: POST /admin/users: {}", userCreateDto);
        UserDto createdUser = service.saveUser(userCreateDto);
        log.info("Request POST /admin/users processed: user={} is created", createdUser);
        return createdUser;
    }
}
