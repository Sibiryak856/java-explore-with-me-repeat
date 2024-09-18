package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.dto.UserCreateDto;
import ru.practicum.user.dto.UserDto;
import ru.practicum.webClient.BaseClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * TODO dto to module
 */

@Controller
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserAdminController {

    @Autowired
    private BaseClient baseClient;

    /*@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto save(@RequestBody @Valid UserCreateDto dto) {
        return  baseClient.getWebClient()
                .post()
                .uri("/admin/users")
                .bodyValue(dto)
                .retrieve()
                .toEntity(UserDto.class);
    }*/

    public List<UserDto> getAll(
            @RequestParam(required = false) List<Long> ids,
            @PositiveOrZero @RequestParam(value = "from", defaultValue = "0", required = false) Integer from,
            @Positive @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return null;
    }

    public void delete(@PathVariable long userId) {

    }

}
