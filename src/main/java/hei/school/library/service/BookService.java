package hei.school.library.service;

import hei.school.library.entity.Book;
import hei.school.library.exception.BadRequestException;
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

    public Book addBook(Book bookToPut) {
        if (bookToPut == null){
            throw new BadRequestException("book must be defined");
        }
        if (bookToPut.getId() == null){
            throw new BadRequestException("the book.id must be defined");
        }
        if (bookToPut.getIsbn() == null){
            throw new BadRequestException("the book.isbn must be defined");
        }
        if (bookToPut.getGenre() == null){
            throw new BadRequestException("the book.genre must be defined");
        }
        if (bookToPut.getFormat() == null){
            throw new BadRequestException("the book.format must be defined");
        }


        return bookRepository.save(bookToPut);
    }
}
