package hei.school.library.controller;

import hei.school.library.Exeception.BookNotFoundException;
import hei.school.library.service.BookService;
import hei.school.library.service.BookVerificationService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerVerifyTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookVerificationService bookVerificationService;

    @Test
    void should_return_200_when_book_exists() throws Exception {
        String isbn = "24765488166265";
        doNothing().when(bookVerificationService).checkBookExists(isbn);

        mockMvc.perform(get("/books/verify/{isbn}", isbn))
                .andExpect(status().isOk());
    }

    @Test
    void should_throw_when_book_not_exists_BUG_should_return_404() {
        String isbn = "24765488166265";
        doThrow(new BookNotFoundException(isbn)).when(bookVerificationService).checkBookExists(isbn);

        assertThrows(ServletException.class, () ->
                mockMvc.perform(get("/books/verify/{isbn}", isbn))
        );
    }
 }
git checkout -b feat/mahefa-test