package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.Stock;

@Service
public interface StockService {
    Stock create(Stock entity);
    List<Stock> getAll();
    Stock addToStock(Long id, Long qty);
    Stock getFromStock(Long id, Long qty);
    Stock updateStock(Long id, Long qty);
    Stock getByItem(Item entity);
}
