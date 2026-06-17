package hei.school.library.service;

import hei.school.library.entity.Book;
import hei.school.library.exception.BadRequestException;
import hei.school.library.repository.BookRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class BookService {
  private final BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book addBook(Book bookToPut) {
    if (bookToPut == null) {
      throw new BadRequestException("book must be defined");
    }
    if (bookToPut.getId() == null) {
      throw new BadRequestException("the book.id must be defined");
    }
    if (bookToPut.getIsbn() == null) {
      throw new BadRequestException("the book.isbn must be defined");
    }
    if (bookToPut.getGenre() == null) {
      throw new BadRequestException("the book.genre must be defined");
    }
    if (bookToPut.getBookFormat() == null) {
      throw new BadRequestException("the book.format must be defined");
    }
    return bookRepository.save(bookToPut);
  }

  public void deleleteBook(String id) {
    bookRepository.deleteById(id);
  }

  public Book getBookByTitle(String title) {
    return bookRepository.findBytitle(title);
  }

  public List<Book> getBookByAuthorName(@RequestParam String name) {
    return bookRepository.findByAuthorName(name);
  }

  public Book getBookById(UUID id) {
    if (id == null) throw new BadRequestException("the book.id must be defined");
    if (id.toString().isEmpty()) throw new BadRequestException("the book.id can't be empty");
    if (id.toString().isBlank()) throw new BadRequestException("the book.id can't be blank");
    return bookRepository.findBookById(id.toString());
  }
}
