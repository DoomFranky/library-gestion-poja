package hei.school.library.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "stock_movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "movement_id")
  private Integer movementId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "warehouse_id")
  private Warehouse warehouse;

  @Column(name = "movement_type", length = 20)
  private String movementType;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "reason", length = 255)
  private String reason;

  @Column(name = "reference_id")
  private Integer referenceId;

  @Column(name = "created_by", length = 100)
  private String createdBy;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
