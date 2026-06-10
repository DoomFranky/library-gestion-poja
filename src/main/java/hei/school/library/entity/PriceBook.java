package hei.school.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @JoinColumn(name = "fk_book_copy")
    private BookCopy bookCopy;
}
