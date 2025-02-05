package relatosdepapel.ms_payments;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import relatosdepapel.ms_payments.data.OrderItemRepository;
import relatosdepapel.ms_payments.data.OrderRepository;
import relatosdepapel.ms_payments.data.model.data.Order;
import relatosdepapel.ms_payments.data.model.data.OrderItem;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
@Profile("dev")
public class DatabaseSeeder {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        return args -> {
            orderItemRepository.deleteAll();
            orderRepository.deleteAll();

            Order order1 = Order.builder()
                    .userId(1L)
                    .createdAt(Timestamp.from(Instant.now()))
                    .updatedAt(Timestamp.from(Instant.now()))
                    .build();

            Order order2 = Order.builder()
                    .userId(2L)
                    .createdAt(Timestamp.from(Instant.now()))
                    .updatedAt(Timestamp.from(Instant.now()))
                    .build();

            order1 = orderRepository.save(order1);
            order2 = orderRepository.save(order2);

            List<OrderItem> orderItems = List.of(
                    new OrderItem(null, order1, 1L, 2), // Pedido del usuario 1, comprando 2 libros con ID 1
                    new OrderItem(null, order1, 3L, 1), // Pedido del usuario 1, comprando 1 libro con ID 3
                    new OrderItem(null, order2, 2L, 4), // Pedido del usuario 2, comprando 4 libros con ID 2
                    new OrderItem(null, order2, 4L, 1)  // Pedido del usuario 2, comprando 1 libro con ID 4
            );

            orderItemRepository.saveAll(orderItems);
            System.out.println("✅ Base de datos de pagos inicializada con datos de prueba");
        };
    }
}
