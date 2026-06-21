package hei.school.library.conf.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import hei.school.library.dto.request.BookRequest;
import hei.school.library.entity.Book;
import hei.school.library.entity.GenreEnum;
import hei.school.library.exception.BadRequestException;
import hei.school.library.repository.BookRepository;
import hei.school.library.service.BookService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
  @Mock private BookRepository bookRepository;

  @InjectMocks private BookService bookService;

  List<Book> library;

  @BeforeEach
  void set (){
    library = new ArrayList<>();

  }
  @Nested
  class getBooks {
    @Test
    void getEmptyListOfBook() { // this test only work when the database is empty
      List<Book> emptyList = new ArrayList<>();
      when(bookRepository.findAll()).thenReturn(emptyList);

      List<Book> getBooks = bookService.getAllBooks();
      assertEquals(emptyList, getBooks);
    }
  }

  @Nested
  class putBooks {
    @Test
    void putANewBookWithGood() {
      BookRequest bookToPut = new BookRequest();
      assertThrows(BadRequestException.class, () -> bookService.addBook(bookToPut));
    }

    @Test
    void putANewTestBook() {
      Book book = new Book(UUID.randomUUID().toString(),"something","Test",LocalDate.now(),"description",
              null,GenreEnum.ART,Instant.now(),
              null,new ArrayList<>(),null);
      when(bookRepository.save(any(Book.class))).thenReturn(book);
      BookRequest bookToPut = new BookRequest(
              "something","Test", LocalDate.now(),"add test book",
              null, GenreEnum.ART, Instant.now(),
              null,new ArrayList<>(),null);
      Book result = bookService.addBook(bookToPut);
      assertNotNull(result);

      assertEquals(book,result);

    }


  }
}
