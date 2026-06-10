package hei.school.library.controller;

import hei.school.library.entity.BookFormat;
import hei.school.library.service.BookFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/formats")
public class BookFormatController {

    @Autowired
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
    public ResponseEntity<BookFormat> getFormatById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookFormatService.getFormatById(id));
    }

    @GetMapping("/label/{label}")
    public ResponseEntity<BookFormat> getFormatByLabel(@PathVariable String label) {
        return ResponseEntity.ok(bookFormatService.getFormatByLabel(label));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookFormat>> searchFormats(@RequestParam String label) {
        return ResponseEntity.ok(bookFormatService.searchByLabel(label));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookFormat> updateFormat(@PathVariable Integer id, @RequestBody BookFormat format) {
        return ResponseEntity.ok(bookFormatService.updateFormat(id, format));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormat(@PathVariable Integer id) {
        bookFormatService.deleteFormat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/books-count")
    public ResponseEntity<Long> getBooksCountByFormat(@PathVariable Integer id) {
        return ResponseEntity.ok(bookFormatService.getBooksCountByFormat(id));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<BookFormat>> getPopularFormats(@RequestParam(defaultValue = "5") int minBooks) {
        return ResponseEntity.ok(bookFormatService.getPopularFormats(minBooks));
    }
}