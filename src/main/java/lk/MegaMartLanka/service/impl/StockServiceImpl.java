package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.Stock;
import lk.MegaMartLanka.repo.StockRepo;
import lk.MegaMartLanka.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional


public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepo stockRepository;

    @Override
    public Stock create(Stock entity) {
        Stock news = stockRepository.save (entity);
        return news;
    }

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll ();
    }

    @Override
    public Stock addToStock(Long id, Long qty) {

        Stock exStok = stockRepository.findById (id).orElse (null);

        if (exStok == null) {
            return null;
        } else {
            exStok.setQoh (exStok.getQoh () + qty);
            return stockRepository.save (exStok);
        }

    }

    @Override
    public Stock getFromStock(Long id, Long qty) {

        Stock exStok = stockRepository.findById (id).orElse (null);

        if (exStok == null) {
            return null;
        } else {
            exStok.setQoh (exStok.getQoh () - qty);
            return stockRepository.save (exStok);
        }

    }

    @Override
    public Stock getByItem(Item entity) {
        return stockRepository.findByItem (entity).orElse (null);
    }

    @Override
    public Stock updateStock(Long id, Long qty) {

        Stock exStok = stockRepository.findById (id).orElse (null);

        if (exStok == null) {
            return null;
        } else {

            exStok.setQoh (qty);
            return stockRepository.save (exStok);
        }
    }

}
