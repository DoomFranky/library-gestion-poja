package hei.school.library.service;

import hei.school.library.entity.Book;
import hei.school.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> findAll () {
        return bookRepository.findAll();
    }
}
