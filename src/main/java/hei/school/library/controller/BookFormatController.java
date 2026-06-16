package hei.school.library.controller;

import hei.school.library.entity.BookFormat;
import hei.school.library.service.BookFormatService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formats")
@AllArgsConstructor
public class BookFormatController {

  private BookFormatService bookFormatService;

  @PostMapping
  public ResponseEntity<BookFormat> createFormat(@RequestBody BookFormat format) {
    BookFormat createdFormat = bookFormatService.createFormat(format);
    return new ResponseEntity<>(createdFormat, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BookFormat>> getAllFormats() {
    return ResponseEntity.ok(bookFormatService.getAllFormats());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookFormat> getFormatById(@PathVariable String id) {
    return ResponseEntity.ok(bookFormatService.getFormatById(id));
  }

  @GetMapping("/label/{label}")
  public ResponseEntity<BookFormat> getFormatBySize(@PathVariable String size) {
    return ResponseEntity.ok(bookFormatService.getFormatBySize(size));
  }

  @GetMapping("/search")
  public ResponseEntity<List<BookFormat>> searchFormats(@RequestParam String size) {
    return ResponseEntity.ok(bookFormatService.searchBySize(size));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookFormat> updateFormat(
      @PathVariable String id, @RequestBody BookFormat format) {
    return ResponseEntity.ok(bookFormatService.updateFormat(id, format));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFormat(@PathVariable String id) {
    bookFormatService.deleteFormat(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/books-count")
  public ResponseEntity<Long> getBooksCountByFormat(@PathVariable String id) {
    return ResponseEntity.ok(bookFormatService.getBooksCountByFormat(id));
  }

  @GetMapping("/popular")
  public ResponseEntity<List<BookFormat>> getPopularFormats(
      @RequestParam(defaultValue = "5") int minBooks) {
    return ResponseEntity.ok(bookFormatService.getPopularFormats(minBooks));
  }
}
