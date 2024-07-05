package lk.Ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class OrderDetailEntity {
    private String productId;
    private String orderId;
    private int quantity;
    private int UnitPrice;

}
