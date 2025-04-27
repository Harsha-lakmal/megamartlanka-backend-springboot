package lk.MegaMartLanka.service;

import lk.MegaMartLanka.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAll();

    Order create(Order entity);
}
