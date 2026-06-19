package hei.school.library.controller;

import hei.school.library.entity.Book;
import hei.school.library.exception.BadRequestException;
import hei.school.library.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;

  @GetMapping
  public List<Book> getBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getBookById(@PathVariable String id) {
    try {
      Book book = bookService.getBookById(id);
      if (book == null) return ResponseEntity.notFound().build();
      return ResponseEntity.ok().body(book);
    } catch (BadRequestException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PostMapping("/add")
  public ResponseEntity<?> putBooks(@RequestBody Book bookToPut) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookToPut));
    } catch (BadRequestException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestBody String id) {
    try {
      bookService.deleleteBook(id);
      return ResponseEntity.ok().body("book delete");
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}
