package hei.school.library.dto.request;

import hei.school.library.entity.Author;
import hei.school.library.entity.BookFormat;
import hei.school.library.entity.GenreEnum;
import hei.school.library.entity.Library;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequest {
  private String isbn;

  private String title;

  private LocalDate publishDate;

  private String description;

  private String urlImage;

  private GenreEnum genre;

  private List<Author> author;

  private List<BookFormat> bookFormat;

  private Library library;
}
