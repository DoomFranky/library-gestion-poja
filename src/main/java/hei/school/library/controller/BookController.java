package hei.school.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.library.entity.Book;
import hei.school.library.service.BookService;


@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    public BookController ( BookService bookService){
        this.bookService=bookService;
    }

     @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

     @PostMapping
    public Book add(@RequestBody Book book) {
        return bookService.ajouterLivre(book);
    }
}
