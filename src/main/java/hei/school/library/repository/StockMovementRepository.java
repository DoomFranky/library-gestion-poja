package hei.school.library.repository;

import hei.school.library.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, String> {
  @Query(
      "SELECT SUM("
          + "CASE WHEN sm.movement_type='OUT' THEN sm.quantity*-1 ELSE sm.quantity"
          + ") ,b.title, bf.format FROM book b "
          + "JOIN b.book_format bf "
          + "JOIN bf.book_copy bc "
          + "JOIN bc.stockMovement sm "
          + "WHERE sm.book_id = :book_id ORDER BY (b.title,bf.format)")
  Integer findQuantityOfBookById(@Param("book_id") String id);
}
