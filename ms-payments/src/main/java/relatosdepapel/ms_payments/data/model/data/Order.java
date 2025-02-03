package relatosdepapel.ms_payments.data.model.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    //@ManyToOne
    //@JoinColumn(name = "coupon_id")
    //@Column(name = "coupon_id")
    //private Long coupon;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

}
