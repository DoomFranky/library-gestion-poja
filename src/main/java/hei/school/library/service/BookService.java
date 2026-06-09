package hei.school.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

   public Book ajouterLivre(Book livre) {
        return bookRepository.save(livre);
    } 
    
      public void supprimerLivre(Integer id) {
        bookRepository.deleteById(id);
    }
}
