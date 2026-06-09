package hei.school.library.entity;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Entity
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

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    private Instant createDatetime;

    @Enumerated(EnumType.STRING)
    private Format format;

}
