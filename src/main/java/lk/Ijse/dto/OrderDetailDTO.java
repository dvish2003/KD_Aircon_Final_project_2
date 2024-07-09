package lk.Ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class OrderDetailDTO {
    private String productId;
    private String orderId;
    private int quantity;
    private int UnitPrice;

}
