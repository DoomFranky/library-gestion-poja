package hei.school.library.service;

import hei.school.library.dto.request.AuthorRequest;
import hei.school.library.entity.Author;
import hei.school.library.repository.AuthorRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public Author getAuthor(String fullName) {
    return authorRepository.findByname(fullName);
  }

  public Author getAuthorById(String id) {
    return authorRepository.findAuthorsById(id);
  }

  public Author addAuthor(AuthorRequest author) {
    return authorRepository.save(new Author(UUID.randomUUID().toString(), author.getFullName()));
  }
}
