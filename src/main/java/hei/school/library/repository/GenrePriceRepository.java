package hei.school.library.repository;

import hei.school.library.dto.request.GenreRevenueDTO;
import hei.school.library.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenrePriceRepository extends JpaRepository<Book, String> {
  @Query(
      """
          SELECT
              b.genre, 
              SUM(pb.unitPrice) 
          FROM OrderCommand o
          JOIN o.books b
          JOIN b.bookFormat bf
          JOIN BookCopy bc ON bc.bookFormat = bf
          JOIN bc.priceBook pb
          GROUP BY b.genre
      """)
  List<GenreRevenueDTO> findRevenueByGenre();
}
