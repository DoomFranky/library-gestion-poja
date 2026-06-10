package hei.school.library.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_format")
public class BookFormat {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String size;
  private Format format;

  @ManyToOne
  @JoinColumn(name = "fk_book")
  private Book books;
}
