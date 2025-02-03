package relatosdepapel.ms_payments.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import relatosdepapel.ms_payments.data.model.data.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByUserId(Long userId);
}
