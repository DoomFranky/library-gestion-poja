package hei.school.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price_book")
public class PriceBook {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private Double unitPrice;
  private LocalDate dateOfPrice;

  @ManyToOne
  @JoinColumn(name = "book_copy_id")
  private BookCopy bookCopy;
}
