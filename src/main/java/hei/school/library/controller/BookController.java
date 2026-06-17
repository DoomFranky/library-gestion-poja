package hei.school.library.controller;

import hei.school.library.entity.Book;
import hei.school.library.service.BookService;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
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
@RequestMapping("/api/book")
public class BookController {
  private final BookService bookService;

  @GetMapping
  public ResponseEntity<?> getBooks() {
    try {
      List<Book> listOfBooks = bookService.getAllBooks();
      return ResponseEntity.ok().body(listOfBooks);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity<?> getBookById(@PathVariable UUID id){
    return ResponseEntity.ok().build();
  }

  @PostMapping("/add")
  public ResponseEntity<?> putBooks(@RequestBody Book bookToPut) {
    try {
      Book book = bookService.addBook(bookToPut);
      return ResponseEntity.ok().body(book);
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
