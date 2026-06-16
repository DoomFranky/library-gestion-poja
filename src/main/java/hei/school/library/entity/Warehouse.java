package hei.school.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "warehouse")
    private List<StockMovement> stockMovements;
}
