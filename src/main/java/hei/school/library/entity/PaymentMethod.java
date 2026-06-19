package hei.school.library.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "payment_method")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_method_id")
  private Integer paymentMethodId;

  @Column(name = "label", length = 80)
  private String label;

  @OneToMany(mappedBy = "paymentMethod")
  private List<Payment> payments;
}
