package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.repository.UserRepository;
import user.UserCreateDto;
import user.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Transactional
    @Override
    public UserDto saveUser(UserCreateDto userCreateDto) {
        return mapper.toDto(repository.save(mapper.toUser(userCreateDto)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAll(List<Long> ids, Pageable pageable) {
        return mapper.toDtoList(repository.findAllByIdIn(ids, pageable));
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
