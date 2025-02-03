package relatosdepapel.ms_payments.service;


import relatosdepapel.ms_payments.data.model.data.Order;
import relatosdepapel.ms_payments.controller.data.CreateOrderRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getOrdersByUserId(Long userId);

    Order createOrder(CreateOrderRequest order);

    Order getOrderById(Long orderId);

    List<Order> getOrders();
}
