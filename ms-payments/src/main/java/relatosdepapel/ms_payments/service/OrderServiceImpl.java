package relatosdepapel.ms_payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import relatosdepapel.ms_payments.data.OrderItemRepository;
import relatosdepapel.ms_payments.data.OrderRepository;
import relatosdepapel.ms_payments.data.model.data.Order;
import relatosdepapel.ms_payments.data.model.data.OrderItem;
import relatosdepapel.ms_payments.controller.data.CreateOrderRequest;
import relatosdepapel.ms_payments.facade.BooksFacade;
import relatosdepapel.ms_payments.facade.model.Book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BooksFacade booksFacade;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, BooksFacade booksFacade) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.booksFacade = booksFacade;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById( Long id ) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }


    @Override
    public Order createOrder(CreateOrderRequest orderRequest) {

        List<Book> books = orderRequest.getItems().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();

        if(books.isEmpty() || books.size() > orderRequest.getItems().size() || books.stream().anyMatch(book -> !book.isVisible())) {
            return null;
        }

        Order order = new Order();

        order.setUserId(orderRequest.getUserId());
        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = orderRequest.getItems().stream()
        .map(itemRequest -> {

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemRequest.getBookId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(savedOrder);

            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }
}
