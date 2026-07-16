package hei.school.library.service;

import hei.school.library.Client.GoogleBooksClient;
import hei.school.library.Client.OpenLibraryClient;
import hei.school.library.Exeception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookVerificationService {
    private final OpenLibraryClient openLibraryClient;
    private final GoogleBooksClient googleBooksClient;

    public BookVerificationService(OpenLibraryClient openLibraryClient, GoogleBooksClient googleBooksClient) {
        this.openLibraryClient = openLibraryClient;
        this.googleBooksClient = googleBooksClient;
    }

    public void checkBookExists(String isbn) {
        boolean exists = openLibraryClient.existsByIsbn(isbn) || googleBooksClient.existsByIsbn(isbn);

        if (!exists) {
            throw new BookNotFoundException(isbn);
        }
    }
}
