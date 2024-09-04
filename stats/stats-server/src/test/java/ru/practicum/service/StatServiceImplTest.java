package ru.practicum.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * альтернативные варианты внутри одного теста!!!
 */

class StatServiceImplTest {

    @Test
    void save() {
    }

    @Test
    void getStat() {
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