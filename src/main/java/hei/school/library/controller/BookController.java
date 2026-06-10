package hei.school.library.controller;

import hei.school.library.entity.Book;
import hei.school.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks () {
        try{
            List<Book> listOfBooks = bookService.findAll();
            return ResponseEntity.ok().body(listOfBooks);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> putBooks (@RequestBody Book bookToPut) {
        try{
            Book book = bookService.addBook(bookToPut);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
