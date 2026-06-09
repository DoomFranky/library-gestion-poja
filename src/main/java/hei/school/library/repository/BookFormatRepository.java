package hei.school.library.repository;

import hei.school.library.entity.BookFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, Integer> {

    // Recherches de base
    BookFormat findByLabel(String label);
    List<BookFormat> findByLabelContainingIgnoreCase(String label);
    boolean existsByLabel(String label);

    // Requête pour trouver les formats populaires
    @Query("SELECT f FROM BookFormat f WHERE SIZE(f.books) >= :minBooks")
    List<BookFormat> findPopularFormats(@Param("minBooks") int minBooks);

    // Compter les livres par format
    @Query("SELECT COUNT(b) FROM BookFormat f JOIN f.books b WHERE f.id = :formatId")
    Long countBooksByFormatId(@Param("formatId") Integer formatId);
}