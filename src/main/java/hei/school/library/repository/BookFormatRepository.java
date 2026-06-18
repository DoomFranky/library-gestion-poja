package hei.school.library.repository;

import hei.school.library.entity.BookFormat;
import java.util.List;

import hei.school.library.entity.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, String> {
}
