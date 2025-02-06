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
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String id) {
        log.info("getOrderById with id {}", id);

        Long orderId = null;
        try {
            orderId = Long.parseLong(id);  // Intentamos convertir el ID a Long
        } catch (NumberFormatException e) {
            // Si no se puede convertir, retornamos un error 400 (Bad Request)
            log.error("Invalid ID format: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // No se encuentra el pedido
        }

        return ResponseEntity.ok(order);  // Si todo está bien, retornamos el pedido
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
