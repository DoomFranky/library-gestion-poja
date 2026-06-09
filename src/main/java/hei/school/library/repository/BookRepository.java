package hei.school.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hei.school.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}