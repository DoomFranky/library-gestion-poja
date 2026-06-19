package hei.school.library.service;

import hei.school.library.entity.BookFormat;
import hei.school.library.repository.BookFormatRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookFormatService {

  private final BookFormatRepository bookFormatRepository;

  public BookFormatService(BookFormatRepository bookFormatRepository) {
    this.bookFormatRepository = bookFormatRepository;
  }

  public BookFormat createFormat(BookFormat format) {
    return bookFormatRepository.save(format);
  }

  public List<BookFormat> getAllFormats() {
    return bookFormatRepository.findAll();
  }

  public BookFormat getFormatById(String id) {
    return bookFormatRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Format non trouvé"));
  }

  public BookFormat updateFormat(String id, BookFormat formatDetails) {
    BookFormat format = getFormatById(id);
    format.setFormat(formatDetails.getFormat());
    return bookFormatRepository.save(format);
  }

  public void deleteFormat(String id) {
    BookFormat format = getFormatById(id);

    bookFormatRepository.delete(format);
  }
}
