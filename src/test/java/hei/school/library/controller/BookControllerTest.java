package hei.school.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hei.school.library.entity.Book;
import hei.school.library.entity.GenreEnum;
import hei.school.library.exception.NotFoundException;
import hei.school.library.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {
  private Book harryPotter;
  private Book hungerGames;

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  @BeforeEach
  void setUp() {
    harryPotter = new Book();
    harryPotter.setId(UUID.randomUUID().toString());
    harryPotter.setTitle("Harry Potter");
    harryPotter.setIsbn("1");
    harryPotter.setBookFormat(new ArrayList<>());
    harryPotter.setGenre(GenreEnum.AVENTURE);
    hungerGames = new Book();
    hungerGames.setId(UUID.randomUUID().toString());
    hungerGames.setTitle("Hunger Games");
    hungerGames.setIsbn("2");
    hungerGames.setBookFormat(new ArrayList<>());
    hungerGames.setGenre(GenreEnum.AVENTURE);
  }

  @Test
  void getBooks_expect200() throws Exception {
    List<Book> listOfBook = new ArrayList<>();
    listOfBook.add(harryPotter);
    listOfBook.add(hungerGames);
    when(bookService.getAllBooks()).thenReturn(listOfBook);
    mockMvc.perform(get("/books")).andExpect(status().isOk());
  }

  @Test
  void getBookByRandomID_shouldReturn404() throws Exception {
    var randomUUID = UUID.randomUUID().toString();
    when(bookService.getBookById(randomUUID)).thenThrow(new NotFoundException("book not found"));

    mockMvc.perform(get("/books/" + randomUUID)).andExpect(status().isNotFound());
  }

  @Test
  void getBookByCorrectID() throws Exception {
    var id = harryPotter.getId();
    when(bookService.getBookById(id)).thenReturn(harryPotter);

    mockMvc.perform(get("/books/" + id)).andExpect(status().isOk());
  }
}
