package hei.school.library.service;

import hei.school.library.dto.request.BookCopyRequest;
import hei.school.library.entity.BookCopy;
import hei.school.library.exception.BadRequestException;
import hei.school.library.exception.NotFoundException;
import hei.school.library.mapper.BookCopyMapper;
import hei.school.library.repository.BookCopyRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCopyService {
  private BookCopyRepository bookCopyRepository;
  private BookCopyMapper bookCopyMapper;

  public List<BookCopy> findAllBookCopy() {
    return bookCopyRepository.findAll();
  }

  public BookCopy findBookCopyById(String id) {
    if (id == null || id.trim().isEmpty() || id.isBlank())
      throw new BadRequestException("the book.id must be defined and not empty or blank");
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("the book.id must be a UUID");
    }
    return bookCopyRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
  }

  public BookCopy addBookCopy(BookCopyRequest bookCopyToPut) {
    if (bookCopyToPut == null) {
      throw new BadRequestException("bookCopy must be defined");
    }
    if (bookCopyToPut.getBookFormat() == null) {
      throw new BadRequestException("the bookCopy.id must be defined");
    }
    if (bookCopyToPut.getPriceBook() == null) {
      throw new BadRequestException("the bookCopy.genre must be defined");
    }
    BookCopy bookCopy = bookCopyMapper.bookCopyRequestToBookCopy(bookCopyToPut);
    return bookCopyRepository.save(bookCopy);
  }

  public void removeBookCopyById(String id) {
    if (id == null || id.trim().isEmpty() || id.isBlank())
      throw new BadRequestException("the book.id must be defined and not empty or blank");
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("the book.id must be a UUID");
    }
    bookCopyRepository.deleteById(id);
  }
}
