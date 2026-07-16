package hei.school.library.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
public class OpenLibraryClient {
    private final RestClient restClient = RestClient.create("https://openlibrary.org");

    public boolean existsByIsbn(String isbn) {
        try {
            var response = restClient.get()
                    .uri("/isbn/{isbn}.json", isbn)
                    .retrieve()
                    .toBodilessEntity();
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
