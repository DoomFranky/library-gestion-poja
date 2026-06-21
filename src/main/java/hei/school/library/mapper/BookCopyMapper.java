package hei.school.library.mapper;

import hei.school.library.dto.request.BookCopyRequest;
import hei.school.library.entity.BookCopy;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {
  public BookCopy bookCopyRequestToBookCopy(BookCopyRequest bookCopyRequest) {
    return new BookCopy(
        UUID.randomUUID().toString(),
        bookCopyRequest.getBookFormat(),
        bookCopyRequest.getPriceBook());
  }
}
