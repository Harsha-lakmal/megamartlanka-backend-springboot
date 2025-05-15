package lk.MegaMartLanka.service;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface
StockService {
    Stock create(Stock entity);

    List<Stock> getAll();

    Stock addToStock(Long id, Long qty);

    Stock getFromStock(Long id, Long qty);

    Stock updateStock(Long id, Long qty);

    Stock getByItem(Item entity);

    String deleteStock(long id);
}
