package hei.school.library.mapper;

import hei.school.library.dto.request.StockMovementRequest;
import hei.school.library.entity.StockMovement;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class StockMovementMapper {
    public StockMovement stockMovementRequestToStockMovement(StockMovementRequest stockMovementToPut) {
        return new StockMovement(UUID.randomUUID().toString(),stockMovementToPut.getBook(),stockMovementToPut.getQuantity(),stockMovementToPut.getReason(),stockMovementToPut.getMovementTypeEnum(), Instant.now());
    }
}
