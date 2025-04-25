package lk.MegaMartLanka.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.entity.StockEntity;





@Repository
public interface StockRepo extends JpaRepository<StockEntity,Long> {
    Optional<StockEntity> findByItem(ItemEntity item);
}
