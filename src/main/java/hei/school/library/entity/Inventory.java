package hei.school.library.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_id")
  private Integer inventoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "warehouse_id")
  private Warehouse warehouse;

  @Column(name = "current_stock")
  private Integer currentStock;

  @Column(name = "reserved_stock")
  private Integer reservedStock;

  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  @PreUpdate
  @PrePersist
  public void onUpdate() {
    this.lastUpdated = LocalDateTime.now();
  }
}
