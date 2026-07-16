package hei.school.library.service;

import hei.school.library.Client.GoogleBooksClient;
import hei.school.library.Client.OpenLibraryClient;
import hei.school.library.Exeception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookVerificationServiceTest {

    @Mock
    private OpenLibraryClient openLibraryClient;

    @Mock
    private GoogleBooksClient googleBooksClient;

    @InjectMocks
    private BookVerificationService bookVerificationService;

    @Test
    void should_not_throw_when_open_library_finds_the_book() {
        String isbn = "9780132350884";
        when(openLibraryClient.existsByIsbn(isbn)).thenReturn(true);

        assertDoesNotThrow(() -> bookVerificationService.checkBookExists(isbn));

        verify(googleBooksClient, never()).existsByIsbn(isbn);
    }

    @Test
    void should_not_throw_when_google_books_finds_the_book() {
        String isbn = "9780132350884";
        when(openLibraryClient.existsByIsbn(isbn)).thenReturn(false);
        when(googleBooksClient.existsByIsbn(isbn)).thenReturn(true);

        assertDoesNotThrow(() -> bookVerificationService.checkBookExists(isbn));
    }

    @Test
    void should_throw_book_not_found_when_no_client_finds_the_book() {
        String isbn = "0000000000000";
        when(openLibraryClient.existsByIsbn(isbn)).thenReturn(false);
        when(googleBooksClient.existsByIsbn(isbn)).thenReturn(false);

        BookNotFoundException exception = assertThrows(
                BookNotFoundException.class,
                () -> bookVerificationService.checkBookExists(isbn)
        );


        assertThat(exception.getMessage()).contains(isbn);
    }
}