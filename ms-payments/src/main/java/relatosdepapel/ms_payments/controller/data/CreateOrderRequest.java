package relatosdepapel.ms_payments.controller.data;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
    private String couponId;

}

