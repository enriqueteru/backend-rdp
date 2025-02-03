package relatosdepapel.ms_payments.controller.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Long bookId;
    private int quantity;

}
