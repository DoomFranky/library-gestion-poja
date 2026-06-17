package hei.school.library.repository;

import hei.school.library.entity.Book;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
  Book findBytitle(String title);
  Book findBookById(String id);
  List<Book> findByAuthorName(String name);
}
