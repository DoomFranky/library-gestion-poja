package hei.school.library.repository;

import hei.school.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findByname(String name);
    Author findAuthorsById(String id);

}
