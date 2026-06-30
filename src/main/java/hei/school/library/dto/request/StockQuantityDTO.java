package hei.school.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockQuantityDTO {
    private Integer quantity;
    private String bookTitle;
    private String bookFormat;
}
