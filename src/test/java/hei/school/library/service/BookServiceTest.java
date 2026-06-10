package hei.school.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import hei.school.library.entity.Book;
import hei.school.library.exception.BadRequestException;
import hei.school.library.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
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
      Book book = new Book();

      assertThrows(BadRequestException.class, () -> bookService.addBook(book));
    }
  }
}
