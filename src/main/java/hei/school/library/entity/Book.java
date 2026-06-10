package hei.school.library.entity;

import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="book")
public class Book {
    @Id
    private Integer id;

    private String isbn;

    private String title;

    private LocalDate publishDate;

    private Double unitPrice;

    private String description;

    private String urlImage;
  
    @OneToOne
    private BookGenre genre;

    @OneToOne
    private BookFormat format;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    private Instant createDatetime;

    @ManyToOne
    @JoinColumn(name="idAuthor")
    private Author author;

    @Enumerated(EnumType.STRING)
    private Format format;

}
