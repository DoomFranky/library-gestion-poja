package hei.school.library.mapper;

import hei.school.library.dto.request.BookRequest;
import hei.school.library.entity.Book;

import java.time.Instant;
import java.util.UUID;

public class BookMapper {
    public Book bookRequestToBook(BookRequest bookRequest) {
        return new Book(UUID.randomUUID().toString(),bookRequest.getIsbn(),bookRequest.getTitle(),
                bookRequest.getPublishDate(),bookRequest.getDescription(),bookRequest.getUrlImage(),
                bookRequest.getGenre(), Instant.now(),bookRequest.getAuthor(),bookRequest.getBookFormat(),
                bookRequest.getLibrary());
    }
}
