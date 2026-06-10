package hei.school.library.service;

import hei.school.library.entity.BookFormat;
import hei.school.library.repository.BookFormatRepository;
import hei.school.library.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookFormatService {

  @Autowired private BookFormatRepository bookFormatRepository;

  @Autowired private BookRepository bookRepository;

  // CREATE
  public BookFormat createFormat(BookFormat format) {
    if (bookFormatRepository.existsByLabel(format.getLabel())) {
      throw new RuntimeException("Un format avec ce label existe déjà");
    }
    return bookFormatRepository.save(format);
  }

  // READ
  public List<BookFormat> getAllFormats() {
    return bookFormatRepository.findAll();
  }

  public BookFormat getFormatById(Integer id) {
    return bookFormatRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Format non trouvé"));
  }

  public BookFormat getFormatByLabel(String label) {
    return bookFormatRepository.findByLabel(label);
  }

  public List<BookFormat> searchByLabel(String label) {
    return bookFormatRepository.findByLabelContainingIgnoreCase(label);
  }

  // UPDATE
  public BookFormat updateFormat(Integer id, BookFormat formatDetails) {
    BookFormat format = getFormatById(id);
    format.setLabel(formatDetails.getLabel());
    return bookFormatRepository.save(format);
  }

  // DELETE
  public void deleteFormat(Integer id) {
    BookFormat format = getFormatById(id);

    // Nettoyer les relations ManyToMany avant suppression
    format
        .getBooks()
        .forEach(
            book -> {
              book.getFormats().remove(format);
              bookRepository.save(book);
            });

    bookFormatRepository.delete(format);
  }

  // Méthodes statistiques
  public Long getBooksCountByFormat(Integer formatId) {
    return bookFormatRepository.countBooksByFormatId(formatId);
  }

  public List<BookFormat> getPopularFormats(int minBooks) {
    return bookFormatRepository.findPopularFormats(minBooks);
  }
}
