package hei.school.library.controller;

import hei.school.library.dto.request.GenreRevenueDTO;
import hei.school.library.service.RevenueService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/revenues")
public class GenreRevenuController {
  private final RevenueService revenueService;

  @GetMapping("/by-genre")
  public List<GenreRevenueDTO> getRevenueByGenre() {
    return revenueService.getRevenueByGenre();
  }
}
