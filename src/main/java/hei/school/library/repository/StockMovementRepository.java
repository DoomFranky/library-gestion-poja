package hei.school.library.repository;

import hei.school.library.dto.request.StockQuantityDTO;
import hei.school.library.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, String> {
  @Query(
      "SELECT SUM("
          + "CASE WHEN sm.movement_type='OUT' THEN sm.quantity * -1 ELSE sm.quantity END"
          + ") ,b.title, bf.format FROM Book b "
          + "JOIN b.bookFormat bf "
          + "JOIN bf.bookCopy bc "
          + "JOIN bc.stockMovement sm "
          + "WHERE sm.book_id = :book_id ORDER BY (b.title, bf.format)")
  StockQuantityDTO findQuantityOfBookById(@Param("book_id") String id);

  @Query(
      "SELECT SUM("
          + "CASE WHEN sm.movement_type='OUT' THEN sm.quantity * -1 ELSE sm.quantity END "
          + "), b.title,bf.format FROM Book b "
          + "JOIN b.bookFormat bf "
          + "JOIN bf.bookCopy bc "
          + "JOIN bc.stockMovement sm "
          + "WHERE sm.book_copy_id = :book_copy_id ORDER BY (b.title,bf.format)")
  StockQuantityDTO findQuantityOfBookCopyById(@Param("book_copy_id") String id);
}
