package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.entity.StockEntity;

@Service
public interface StockService {
    StockEntity create(StockEntity entity);
    List<StockEntity> getAll();
    StockEntity addToStock(Long id, Long qty);
    StockEntity getFromStock(Long id, Long qty);
    StockEntity updateStock(Long id, Long qty);
    StockEntity getByItem(ItemEntity entity);
}
