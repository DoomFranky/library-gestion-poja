package hei.school.library.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "sale_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sale_item_id")
  private Integer saleItemId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sale_id")
  private Sale sale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "unit_price", precision = 10, scale = 2)
  private BigDecimal unitPrice;

  @Column(name = "discount", precision = 5, scale = 2)
  private BigDecimal discount;
}
