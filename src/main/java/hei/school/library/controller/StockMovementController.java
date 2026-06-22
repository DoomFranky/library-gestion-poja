package hei.school.library.controller;

import hei.school.library.dto.request.StockMovementRequest;
import hei.school.library.entity.MovementTypeEnum;
import hei.school.library.entity.StockMovement;
import hei.school.library.exception.BadRequestException;
import hei.school.library.exception.NotFoundException;
import hei.school.library.service.StockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StockMovementController {
    private StockMovementService stockMovementService;

    @GetMapping
    public List<StockMovement> getStockMovements() {
        return stockMovementService.getAllStockMovements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockMovementById(@PathVariable String id) {
        try {
            StockMovement stockMovement = stockMovementService.getStockMovementById(id);
            if (stockMovement == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(stockMovement);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> putStockMovements(@RequestBody StockMovementRequest stockMovementToPut) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(stockMovementService.addStockMovement(stockMovementToPut));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody String id) {
        try {
            stockMovementService.deleleteStockMovement(id);
            return ResponseEntity.ok().body("stockMovement delete");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public Integer getStockQuantityOfBookById(String id){
        return stockMovementService.findStockQuantityOfBookById(id);
    }
}
