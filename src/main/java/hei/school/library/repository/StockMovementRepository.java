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
      "SELECT SUM(CASE WHEN sm.movementTypeEnum= hei.school.library.entity.MovementTypeEnum.OUT"
          + " THEN sm.quantity * -1 ELSE sm.quantity END) ,b.title, bf.format FROM Book b JOIN"
          + " b.bookFormat bf JOIN BookCopy bc ON bc.bookFormat = bf JOIN StockMovement sm ON"
          + " sm.bookCopy = bc WHERE b.id = :bookId ORDER BY (b.title, bf.format)")
  StockQuantityDTO findQuantityOfBookById(@Param("bookId") String id);

  @Query(
      "SELECT SUM(CASE WHEN sm.movementTypeEnum= hei.school.library.entity.MovementTypeEnum.OUT"
          + " THEN sm.quantity * -1 ELSE sm.quantity END ), b.title,bf.format FROM Book b JOIN"
          + " b.bookFormat bf JOIN BookCopy bc ON bc.bookFormat = bf JOIN StockMovement sm ON"
          + " sm.bookCopy = bc WHERE bc.id = :bookCopyId ORDER BY (b.title,bf.format)")
  StockQuantityDTO findQuantityOfBookCopyById(@Param("bookCopyId") String id);
}
