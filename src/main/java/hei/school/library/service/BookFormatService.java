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
    if (bookFormatRepository.existsBySize(format.getFormat().toString())) {
      throw new RuntimeException("Un format avec ce label existe déjà");
    }
    return bookFormatRepository.save(format);
  }

  // READ
  public List<BookFormat> getAllFormats() {
    return bookFormatRepository.findAll();
  }

  public BookFormat getFormatById(String id) {
    return bookFormatRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Format non trouvé"));
  }

  public BookFormat getFormatBySize(String size) {
    return bookFormatRepository.findBySize(size);
  }

  public List<BookFormat> searchBySize(String size) {
    return bookFormatRepository.findBySizeContainingIgnoreCase(size);
  }

  // UPDATE
  public BookFormat updateFormat(String id, BookFormat formatDetails) {
    BookFormat format = getFormatById(id);
    format.setFormat(formatDetails.getFormat());
    return bookFormatRepository.save(format);
  }

  // DELETE
  public void deleteFormat(String id) {
    BookFormat format = getFormatById(id);

    bookFormatRepository.delete(format);
  }

  // Méthodes statistiques
  public Long getBooksCountByFormat(String formatId) {
    return bookFormatRepository.countBooksByFormatId(formatId);
  }

  public List<BookFormat> getPopularFormats(int minBooks) {
    return bookFormatRepository.findPopularFormats(minBooks);
  }
}
