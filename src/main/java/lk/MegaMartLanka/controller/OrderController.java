package lk.MegaMartLanka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.MegaMartLanka.dto.OrderDto;
import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.entity.OrderEntity;
import lk.MegaMartLanka.service.ItemService;
import lk.MegaMartLanka.service.OrderService;

@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAll() {
        List<OrderEntity> orders = orderService.getAll();

        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderEntity> create(@RequestBody OrderDto dto) {

        OrderEntity newOrder = new OrderEntity();
        Double total = 0.0;
        List<ItemEntity> orderItems = new ArrayList<ItemEntity>();

        for (Long id : dto.getItemIds()) {
            ItemEntity item = itemService.getById(id);
            if (item != null) {
                orderItems.add(item);
                total += item.getPrice();
            }
        }

        newOrder.setItems(orderItems);
        newOrder.setOrderTotal(total);

        OrderEntity createdOrder = orderService.create(newOrder);

        return ResponseEntity.status(201).body(createdOrder);
    }

}
