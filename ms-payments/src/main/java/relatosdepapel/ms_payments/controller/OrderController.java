package relatosdepapel.ms_payments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import relatosdepapel.ms_payments.data.model.data.Order;
import relatosdepapel.ms_payments.controller.data.CreateOrderRequest;
import relatosdepapel.ms_payments.service.OrderServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("getAllOrders");
        List<Order> orders = orderService.getOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String id) {
        log.info("getOrderById with id {}", id);
        Order order = orderService.getOrderById(Long.parseLong(id));
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("createOrder with request {}", request);
        System.out.println(request.toString());
        Order order = orderService.createOrder(request);
        if (order != null) {

            return ResponseEntity.ok(order);
        }
        log.info("createOrder returned null");
        return ResponseEntity.badRequest().build();
    }
}
