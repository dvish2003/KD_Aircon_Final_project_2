package lk.Ijse.Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class OrderDetail {
    private String orderId;
    private String productId;
    private int quantity;
    private int UnitPrice;

}
