package hei.school.library.controller;

import hei.school.library.entity.Book;
import hei.school.library.entity.GenreEnum;
import hei.school.library.entity.MovementTypeEnum;
import hei.school.library.entity.StockMovement;
import hei.school.library.service.BookService;
import hei.school.library.service.StockMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockMouvementControllerTest.class)
public class StockMouvementControllerTest {
    private StockMovement stockMovement;
    private StockMovement stockMovement2;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StockMovementService stockMovementService;
    @BeforeEach
    void setUp() {
        Book hungerGames = new Book();
        hungerGames.setId(UUID.randomUUID().toString());
        hungerGames.setTitle("Hunger Games");
        hungerGames.setIsbn("2");
        hungerGames.setBookFormat(new ArrayList<>());
        hungerGames.setGenre(GenreEnum.AVENTURE);

        Book harryPotter = new Book();
        harryPotter.setId(UUID.randomUUID().toString());
        harryPotter.setTitle("Harry Potter");
        harryPotter.setIsbn("1");
        harryPotter.setBookFormat(new ArrayList<>());
        harryPotter.setGenre(GenreEnum.AVENTURE);

        StockMovement stockMovement = new StockMovement();
        stockMovement.setId("23");
        stockMovement.setBook(harryPotter);
        stockMovement.setQuantity(14);
        stockMovement.setReason("increase a stock of Harry Potter");
        stockMovement.setMovementTypeEnum(MovementTypeEnum.IN);
        stockMovement.setCreatedAt(Instant.now());

        StockMovement stockMovement2 = new StockMovement();
        stockMovement2.setId("24");
        stockMovement2.setBook(hungerGames);
        stockMovement2.setQuantity(14);
        stockMovement2.setReason("decrease a stock of hunger_games");
    }

    @Test
    void getAllStockMovements() throws Exception {
        List<StockMovement>list = new ArrayList<>();
        list.add(stockMovement);
        list.add(stockMovement2);
        when(stockMovementService.getAllStockMovements()).thenReturn(list);
        mockMvc.perform(get("/stockmovement")).andExpect(status().isOk());
    }

    @Test
    void getStockMovementById() throws Exception {
        when(stockMovementService.getStockMovementById("23")).thenReturn(stockMovement);
        mockMvc.perform(get("/stockmovement/23")).andExpect(status().isOk());
    }

}
