package relatosdepapel.ms_payments.data;

import org.springframework.data.jpa.repository.JpaRepository;
import relatosdepapel.ms_payments.data.model.data.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
