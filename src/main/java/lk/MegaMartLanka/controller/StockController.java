package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.dto.StockDto;
import lk.MegaMartLanka.entity.Stock;
import lk.MegaMartLanka.service.ItemService;
import lk.MegaMartLanka.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/MegaMartLanka")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAll() {
        List<Stock> stock = stockService.getAll ();

        return ResponseEntity.status (200).body (stock);
    }

    @PostMapping("/stock")
    public ResponseEntity<Stock> create(@RequestBody StockDto dto) {

        Stock newStock = new Stock ();

        if (itemService.getById (dto.getId ()) == null) {
            return ResponseEntity.status (404).body (null);
        } else {
            newStock.setItem (itemService.getById (dto.getId ()));
            newStock.setQoh (dto.getQty ());

            if (stockService.getByItem (newStock.getItem ()) == null) {
                Stock createdStock = stockService.create (newStock);
                return ResponseEntity.status (201).body (createdStock);
            } else {
                return ResponseEntity.status (403).body (null);
            }
        }
    }

    @PutMapping("/stock/addto")
    public ResponseEntity<List<Stock>> addToStock(@RequestBody List<StockDto> dtos) { // here dto.id is stock id
        List<Stock> updatedList = new ArrayList<> ();
        for (StockDto stockDto : dtos) {
            Stock updated = stockService.addToStock (stockDto.getId (), stockDto.getQty ());
            if (updated != null) {
                updatedList.add (updated);
            }
        }
        return ResponseEntity.status (201).body (updatedList);
    }

    @PutMapping("/stock/getfrom")
    public ResponseEntity<List<Stock>> getFromStock(@RequestBody List<StockDto> dtos) {
        List<Stock> updatedList = new ArrayList<> ();
        for (StockDto stockDto : dtos) {
            Stock updated = stockService.getFromStock (stockDto.getId (), stockDto.getQty ());
            if (updated != null) {
                updatedList.add (updated);
            }
        }
        return ResponseEntity.status (201).body (updatedList);
    }

    @PutMapping("/stock")
    public ResponseEntity<Stock> updateStock(@RequestBody StockDto dto) {

        Stock updatedStock = stockService.updateStock (dto.getId (), dto.getQty ());

        if (updatedStock == null) {
            return ResponseEntity.status (404).body (null);
        } else {
            return ResponseEntity.status (200).body (updatedStock);
        }
    }
    @DeleteMapping("/delete/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable long id) {
        String stockDelete = stockService.deleteStock (id);
        return ResponseEntity.status (200).body (stockDelete);
    }
}
