package hei.school.library.controller;

import hei.school.library.dto.request.BookCopyRequest;
import hei.school.library.entity.BookCopy;
import hei.school.library.exception.BadRequestException;
import hei.school.library.exception.NotFoundException;
import hei.school.library.service.BookCopyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/books/copy")
public class BookCopyController {
  private BookCopyService bookCopyService;

  @GetMapping
  public List<BookCopy> getAllBookCopy() {
    return bookCopyService.findAllBookCopy();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getBookCopyById(@PathVariable String id) {
    try {
      return ResponseEntity.ok(bookCopyService.findBookCopyById(id));
    } catch (BadRequestException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PostMapping("/add")
  public ResponseEntity<?> postBookCopyById(@RequestParam BookCopyRequest bookCopy) {
    try {
      return ResponseEntity.status(201).body(bookCopyService.addBookCopy(bookCopy));
    } catch (BadRequestException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteBookCopyById(@PathVariable String id) {
    try {
      bookCopyService.removeBookCopyById(id);
      return ResponseEntity.ok("book copy:" + id + " is deleted");
    } catch (BadRequestException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}
