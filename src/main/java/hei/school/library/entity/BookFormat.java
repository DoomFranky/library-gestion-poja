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
@Table(name = "format")
public class BookFormat {

  @Id private Integer id;
  private String label;

  @OneToMany private List<Book> books;
}
