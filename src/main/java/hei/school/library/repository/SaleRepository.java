package hei.school.library.repository;

import hei.school.library.entity.OrderCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<OrderCommand, String> {}