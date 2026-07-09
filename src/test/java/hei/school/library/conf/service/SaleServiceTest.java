package hei.school.library.conf.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hei.school.library.entity.Book;
import hei.school.library.entity.OrderCommand;
import hei.school.library.entity.Status;
import hei.school.library.entity.UserCustomer;
import hei.school.library.exception.BadRequestException;
import hei.school.library.repository.SaleRepository;
import hei.school.library.service.SaleService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
  @Mock private SaleRepository saleRepository;

  @InjectMocks private SaleService saleService;

  private OrderCommand validSale() {
    OrderCommand sale = new OrderCommand();
    sale.setUserCustomer(new UserCustomer());
    sale.setOrderDate(LocalDate.now());
    sale.setStatus(Status.PENDING);
    sale.setBooks(List.of(new Book()));
    sale.setDeliveryAddress("12 rue des Lilas");
    return sale;
  }

  @Nested
  class validateSale {

    @Test
    void throwsWhenSaleIsNull() {
      assertThrows(BadRequestException.class, () -> saleService.validateSale(null));
    }

    @Test
    void throwsWhenUserCustomerIsMissing() {
      OrderCommand sale = validSale();
      sale.setUserCustomer(null);

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void throwsWhenOrderDateIsMissing() {
      OrderCommand sale = validSale();
      sale.setOrderDate(null);

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void throwsWhenOrderDateIsInTheFuture() {
      OrderCommand sale = validSale();
      sale.setOrderDate(LocalDate.now().plusDays(1));

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void throwsWhenStatusIsMissing() {
      OrderCommand sale = validSale();
      sale.setStatus(null);

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void throwsWhenBooksAreEmpty() {
      OrderCommand sale = validSale();
      sale.setBooks(List.of());

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void throwsWhenDeliveryAddressIsBlank() {
      OrderCommand sale = validSale();
      sale.setDeliveryAddress("   ");

      assertThrows(BadRequestException.class, () -> saleService.validateSale(sale));
    }

    @Test
    void doesNotThrowWhenSaleIsValid() {
      OrderCommand sale = validSale();

      assertDoesNotThrow(() -> saleService.validateSale(sale));
    }
  }

  @Nested
  class addSale {

    @Test
    void savesTheSaleWhenValid() {
      OrderCommand sale = validSale();
      when(saleRepository.save(sale)).thenReturn(sale);

      OrderCommand savedSale = saleService.addSale(sale);

      assertEquals(sale, savedSale);
      verify(saleRepository, times(1)).save(sale);
    }

    @Test
    void doesNotSaveTheSaleWhenInvalid() {
      OrderCommand sale = validSale();
      sale.setUserCustomer(null);

      assertThrows(BadRequestException.class, () -> saleService.addSale(sale));
    }
  }
}
