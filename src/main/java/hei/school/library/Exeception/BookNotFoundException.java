package hei.school.library.Exeception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Aucun livre trouvé pour l'ISBN : " + isbn);
    }
}
