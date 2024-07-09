package lk.Ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class ProductShowRoomJoinDTO {
    public String showRoomId;
    private String productId;
    private String productDescription;
    private int productQuantityOnHand;
    private int productUnitPrice;
}
