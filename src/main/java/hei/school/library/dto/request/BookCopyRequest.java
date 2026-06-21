package hei.school.library.dto.request;

import hei.school.library.entity.BookFormat;
import hei.school.library.entity.PriceBook;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCopyRequest {
  private BookFormat bookFormat;
  private List<PriceBook> priceBook;
}
