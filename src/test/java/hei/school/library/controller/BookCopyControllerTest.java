package hei.school.library.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hei.school.library.entity.BookCopy;
import hei.school.library.service.BookCopyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTest {
  private BookCopy harryPotter;

  @MockBean private BookCopyService bookCopyService;
  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    harryPotter = new BookCopy();
    harryPotter.setId("123");
  }

  @Test
  void shouldReturnBookCopyById() throws Exception {
    when(bookCopyService.findBookCopyById("123")).thenReturn(harryPotter);
    mockMvc
        .perform(get("/books/copy/123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("123"));

    verify(bookCopyService).findBookCopyById("123");
  }
}
