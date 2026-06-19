package hei.school.library.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Integer paymentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sale_id")
  private Sale sale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_method_id")
  private PaymentMethod paymentMethod;

  @Column(name = "payment_date")
  private LocalDateTime paymentDate;

  @Column(name = "amount", precision = 12, scale = 2)
  private BigDecimal amount;

  @Column(name = "reference", length = 100)
  private String reference;

  @Column(name = "status", length = 30)
  private String status;
}
