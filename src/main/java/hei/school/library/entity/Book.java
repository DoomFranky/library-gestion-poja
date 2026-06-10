package hei.school.library.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String isbn;

  private String title;

  private LocalDate publishDate;

  private String description;

  private String urlImage;

  @Enumerated(EnumType.STRING)
  private GenreEnum genre;

  private Instant createDatetime;

  @ManyToMany
  @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> author;

  @OneToMany
  private List<BookFormat> bookFormat;

  @ManyToOne
  @JoinColumn(name ="fk_library")
  private Library library;
}
