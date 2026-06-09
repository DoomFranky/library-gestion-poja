package hei.school.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String isbn;

    @OneToOne
    private BookGenre genre;

    @OneToOne
    private BookFormat format;
    private String title;
    private LocalDate publishDate;
    private String description;
    private Instant created_at;


}