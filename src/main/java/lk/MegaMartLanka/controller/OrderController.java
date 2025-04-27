package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.dto.OrderDto;
import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.Order;
import lk.MegaMartLanka.service.ItemService;
import lk.MegaMartLanka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class  OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.getAll ();

        return ResponseEntity.status (200).body (orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@RequestBody OrderDto dto) {

        Order newOrder = new Order ();
        Double total = 0.0;
        List<Item> orderItems = new ArrayList<Item> ();

        for (Long id : dto.getItemIds ()) {
            Item item = itemService.getById (id);
            if (item != null) {
                orderItems.add (item);
                total += item.getPrice ();
            }
        }

        newOrder.setItems (orderItems);
        newOrder.setOrderTotal (total);

        Order createdOrder = orderService.create (newOrder);

        return ResponseEntity.status (201).body (createdOrder);
    }

}
