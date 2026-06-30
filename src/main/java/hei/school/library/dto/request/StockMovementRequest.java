package hei.school.library.dto.request;

import hei.school.library.entity.BookCopy;
import hei.school.library.entity.MovementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockMovementRequest {
  private BookCopy bookCopy;
  private Integer quantity;
  private String reason;
  private MovementTypeEnum movementTypeEnum;
}
