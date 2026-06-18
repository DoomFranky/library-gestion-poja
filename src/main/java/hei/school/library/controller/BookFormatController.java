package hei.school.library.controller;

import hei.school.library.entity.BookFormat;
import hei.school.library.service.BookFormatService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/format")
@AllArgsConstructor
public class BookFormatController {

  private BookFormatService bookFormatService;

  @PostMapping
  public BookFormat createFormat(@RequestBody BookFormat format) {
    return bookFormatService.createFormat(format);
  }

  @GetMapping
  public List<BookFormat> getAllFormats() {
    return bookFormatService.getAllFormats();
  }

  @GetMapping("/{id}")
  public BookFormat getFormatById(@PathVariable String id) {
    return bookFormatService.getFormatById(id);
  }

  @GetMapping("/label/{label}")
  public BookFormat getFormatBySize(@PathVariable String label) {
    return bookFormatService.getFormatBySize(label);
  }

  @GetMapping("/search")
  public List<BookFormat> searchFormats(@RequestParam String size) {
    return bookFormatService.searchBySize(size);
  }

  @PutMapping("/{id}")
  public BookFormat updateFormat(
      @PathVariable String id, @RequestBody BookFormat format) {
    return bookFormatService.updateFormat(id, format);
  }

  @DeleteMapping("/{id}")
  public void deleteFormat(@PathVariable String id) {
    bookFormatService.deleteFormat(id);
  }
}
