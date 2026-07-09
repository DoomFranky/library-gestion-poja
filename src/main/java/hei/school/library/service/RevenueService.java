package hei.school.library.service;

import hei.school.library.dto.request.GenreRevenueDTO;
import hei.school.library.repository.GenrePriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevenueService {
  private final GenrePriceRepository genrePriceRepository;

  public List<GenreRevenueDTO> getRevenueByGenre() {
    return genrePriceRepository.findRevenueByGenre();
  }
}
