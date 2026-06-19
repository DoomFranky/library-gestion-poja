package hei.school.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id")
  private Integer authorId;

  @Column(name = "last_name", length = 100)
  private String lastName;

  @Column(name = "first_name", length = 100)
  private String firstName;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "nationality", length = 80)
  private String nationality;

  @Column(name = "biography", columnDefinition = "TEXT")
  private String biography;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @ManyToMany(mappedBy = "authors")
  private List<Book> books;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
