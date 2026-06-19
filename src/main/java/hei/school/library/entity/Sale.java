package hei.school.library.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sale_id")
  private Integer saleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private OrderCommand order;

  @Column(name = "sale_date")
  private LocalDateTime saleDate;

  @Column(name = "total_amount", precision = 12, scale = 2)
  private BigDecimal totalAmount;

  @Column(name = "status", length = 30)
  private String status;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
  private List<SaleItem> saleItems;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
  private List<Payment> payments;
}
