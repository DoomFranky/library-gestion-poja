package hei.school.library.controller;

import hei.school.library.entity.Book;
import hei.school.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getBooks () {
        try{
            List<Book> listOfBooks = bookService.findAll();
            return ResponseEntity.ok().body(listOfBooks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
