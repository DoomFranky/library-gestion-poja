package hei.school.library.dto.request;

import hei.school.library.entity.GenreEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenreRevenueDTO {
  private GenreEnum genre;
  private Double totalAmount;
}
