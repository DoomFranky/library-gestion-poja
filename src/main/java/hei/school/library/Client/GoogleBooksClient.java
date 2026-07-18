package hei.school.library.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleBooksClient {
    private final RestClient restClient = RestClient.create("https://www.googleapis.com/books/v1");

    @Value("${google.books.api-key:}")
    private String apiKey;

    public boolean existsByIsbn(String isbn) {
        try {
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/volumes")
                            .queryParam("q", "isbn:" + isbn)
                            .queryParam("key", apiKey)
                            .build())
                    .retrieve()
                    .body(GoogleBooksResponse.class);

            return response != null && response.totalItems() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    record GoogleBooksResponse(int totalItems) {}
}
