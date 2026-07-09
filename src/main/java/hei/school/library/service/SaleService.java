package hei.school.library.service;

import hei.school.library.entity.OrderCommand;
import hei.school.library.exception.BadRequestException;
import hei.school.library.repository.SaleRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleService {
  private final SaleRepository saleRepository;

  public OrderCommand addSale(OrderCommand saleToAdd) {
    validateSale(saleToAdd);
    return saleRepository.save(saleToAdd);
  }

  public void validateSale(OrderCommand sale) {
    if (sale == null) {
      throw new BadRequestException("sale must be defined");
    }
    if (sale.getUserCustomer() == null) {
      throw new BadRequestException("the sale.userCustomer must be defined");
    }
    if (sale.getOrderDate() == null) {
      throw new BadRequestException("the sale.orderDate must be defined");
    }
    if (sale.getOrderDate().isAfter(LocalDate.now())) {
      throw new BadRequestException("the sale.orderDate must not be in the future");
    }
    if (sale.getStatus() == null) {
      throw new BadRequestException("the sale.status must be defined");
    }
    List<?> books = sale.getBooks();
    if (books == null || books.isEmpty()) {
      throw new BadRequestException("the sale.books must not be empty");
    }
    if (sale.getDeliveryAddress() == null || sale.getDeliveryAddress().isBlank()) {
      throw new BadRequestException("the sale.deliveryAddress must be defined");
    }
  }
}
