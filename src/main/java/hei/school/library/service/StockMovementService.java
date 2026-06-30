package hei.school.library.service;

import hei.school.library.dto.request.StockMovementRequest;
import hei.school.library.dto.request.StockQuantityDTO;
import hei.school.library.entity.MovementTypeEnum;
import hei.school.library.entity.StockMovement;
import hei.school.library.exception.BadRequestException;
import hei.school.library.exception.NotFoundException;
import hei.school.library.mapper.StockMovementMapper;
import hei.school.library.repository.StockMovementRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockMovementService {
  private final StockMovementRepository stockMovementRepository;

  // bad practice but can't make it work without it
  private final StockMovementMapper stockMovementMapper = new StockMovementMapper();

  public List<StockMovement> getAllStockMovements() {
    return stockMovementRepository.findAll();
  }

  public StockMovement addStockMovement(StockMovementRequest stockMovementToPut) {
    if (stockMovementToPut == null) {
      throw new BadRequestException("stockMovement must be defined");
    }

    if (stockMovementToPut.getBookCopy() == null) {
      throw new BadRequestException("the stockMovement.bookCopy must be defined");
    }
    if (stockMovementToPut.getMovementTypeEnum() == null) {
      throw new BadRequestException("the stockMovement.movementType must be defined");
    }
    if (stockMovementToPut.getQuantity() == null) {
      throw new BadRequestException("the stockMovement.quantity must be defined");
    }
    StockMovement stockMovement =
        stockMovementMapper.stockMovementRequestToStockMovement(stockMovementToPut);
    if (stockMovement.getMovementTypeEnum().equals(MovementTypeEnum.OUT)) {
      if (findStockQuantityOfBookCopyById(stockMovement.getBookCopy().getId()).getQuantity()
          < stockMovement.getQuantity()) {
        throw new BadRequestException(
            "Can't take "
                + stockMovement.getQuantity()
                + " of book, only: "
                + findStockQuantityOfBookCopyById(stockMovement.getBookCopy().getId())
                + " in stock");
      }
    }
    return stockMovementRepository.save(stockMovement);
  }

  public void deleleteStockMovement(String id) {
    stockMovementRepository.deleteById(id);
  }

  public StockMovement getStockMovementById(String id) {
    if (id == null || id.trim().isEmpty() || id.isBlank())
      throw new BadRequestException("the stockMovement.id must be defined and not empty or blank");
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("the stockMovement.id must be a UUID");
    }
    return stockMovementRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("stockMovement not found"));
  }

  public StockQuantityDTO findStockQuantityOfBookById(String id) {
    return stockMovementRepository.findQuantityOfBookById(id);
  }

  private StockQuantityDTO findStockQuantityOfBookCopyById(String id) {
    return stockMovementRepository.findQuantityOfBookCopyById(id);
  }
}
