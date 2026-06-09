package hei.school.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import hei.school.library.entity.Book;
import hei.school.library.repository.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
 private final BookRepository bookRepository; 
 
 public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

   public Book addBook(Book livre) {
        return bookRepository.save(livre);
    } 
    
      public void deleleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book getBookByTitle(String title){
       return bookRepository.findBytitle(title); 
    }

    public List<Book> getBookByAuthorName(@RequestParam String name){
        return bookRepository.findByAuthorName(name);
    }
}
