package hei.school.library.repository;

import hei.school.library.entity.BookFormat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, String> {

  // Recherches de base
  BookFormat findBySize(String size);

  List<BookFormat> findBySizeContainingIgnoreCase(String size);

  boolean existsBySize(String size);

  // Requête pour trouver les formats populaires
  @Query("SELECT f FROM BookFormat f WHERE SIZE(f.book) >= :minBooks")
  List<BookFormat> findPopularFormats(@Param("minBooks") int minBooks);

  // Compter les livres par format
  @Query("SELECT COUNT(b) FROM BookFormat f JOIN f.book b WHERE f.id = :formatId")
  Long countBooksByFormatId(@Param("formatId") String formatId);
}
