package lk.Ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class ProductShowRoomJoinEntity {
    public String showRoomId;
    private String productId;
    private String productDescription;
    private int productQuantityOnHand;
    private int productUnitPrice;
}
