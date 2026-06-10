package hei.school.library.repository;

import hei.school.library.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
  Book findBytitle(String title);

  List<Book> findByAuthorName(String name);
}
