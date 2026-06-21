package hei.school.library.service;

import hei.school.library.dto.request.BookRequest;
import hei.school.library.entity.*;
import hei.school.library.exception.BadRequestException;
import hei.school.library.mapper.BookMapper;
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

  // bad practice but can't make it work without it
  private final BookMapper bookMapper = new BookMapper();

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book addBook(BookRequest bookToPut) {
    if (bookToPut == null) {
      throw new BadRequestException("book must be defined");
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
    Book book = bookMapper.bookRequestToBook(bookToPut);
    return bookRepository.save(book);
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

  public Book getBookById(String id) {
    if (id == null || id.trim().isEmpty() || id.isBlank())
      throw new BadRequestException("the book.id must be defined and not empty or blank");
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("the book.id must be a UUID");
    }
    return bookRepository.findById(id).orElseThrow(() -> new BadRequestException("book not found"));
  }
}
