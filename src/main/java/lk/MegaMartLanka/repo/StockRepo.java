package lk.MegaMartLanka.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.Stock;





@Repository
public interface StockRepo extends JpaRepository<Stock,Long> {
    Optional<Stock> findByItem(Item item);
}
