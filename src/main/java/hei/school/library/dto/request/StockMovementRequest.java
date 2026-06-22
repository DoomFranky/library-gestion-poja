package hei.school.library.dto.request;

import hei.school.library.entity.Book;
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
  private Book book;
  private Double quantity;
  private String reason;
  private MovementTypeEnum movementTypeEnum;
}
