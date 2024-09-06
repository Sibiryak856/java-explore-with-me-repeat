package ru.practicum.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.StatCreateDto;
import ru.practicum.ViewStatDto;
import ru.practicum.mapper.StatMapper;
import ru.practicum.model.StatData;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO альтернативные варианты внутри одного теста!!!
 * TODO exception test?
 * TODO check only once invocation in get test
 */

@ExtendWith(MockitoExtension.class)
class StatServiceImplTest {

    @InjectMocks
    private StatServiceImpl service;

    @Mock
    private StatRepository repository;

    @Spy
    private StatMapper mapper = Mappers.getMapper(StatMapper.class);


    @Test
    void save() {
        when(repository.save(any(StatData.class)))
                .thenReturn(new StatData());

        service.save(new StatCreateDto());

        verify(repository).save(any(StatData.class));
    }

    @Test
    void getHits_whenIpNotUnique_thenFindAllByTimeBetween() {
        when(repository.findAllByTimeBetween(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList()))
                .thenReturn(Collections.emptyList());

        List<ViewStatDto> result = service.getStat(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                List.of("uri"),
                FALSE);

        verify(repository).findAllByTimeBetween(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList());

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void getHits_whenIpUnique_thenFindAllUniqueHitByTimeBetween() {
        when(repository.findAllUniqueHitByTimeBetween(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList()))
                .thenReturn(Collections.emptyList());

        List<ViewStatDto> result = service.getStat(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                List.of("uri"),
                TRUE);

        verify(repository).findAllUniqueHitByTimeBetween(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyList());

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    /*

    Стоит предупредить, что к мокам нужно подходить с осторожностью.
    Это вспомогательный тестовый объект.
    Если переносить в него много логики, увеличится вероятность ошибок в коде теста.
    Лучше придерживаться принципа: для каждого тестируемого сценария создаём отдельный тест со своим моком.
    У мока при этом должно быть простое поведение.
    @Test
void testCreateBookDescriptionComplexLogic() throws SQLException {
    BookService bookService = new BookService();
    AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
    bookService.setAuthorService(mockAuthorService);

    Mockito
        .when(mockAuthorService.getAuthorDescription(anyInt()))
        .thenAnswer(invocationOnMock -> {
            int authorId = invocationOnMock.getArgument(0, Integer.class);
            if (authorId % 2 == 0) {
                //чётные id у русских авторов
                return "великий русский писатель";
            } else {
                //нечётные id у английских авторов
                return "великий английский писатель";
            }
        });

    String bookDescriptionEnglish = bookService.createBookDescription("Гамлет", 1599, 11, "Уильям Шекспир");
    Assertions.assertEquals("Гамлет, 1599 автор Уильям Шекспир, великий английский писатель", bookDescriptionEnglish);

    String bookDescriptionRussian = bookService.createBookDescription("Война и мир", 1898, 6, "Л.Н.Толстой");
    Assertions.assertEquals("Война и мир, 1898 автор Л.Н.Толстой, великий русский писатель", bookDescriptionRussian);
}    */
}