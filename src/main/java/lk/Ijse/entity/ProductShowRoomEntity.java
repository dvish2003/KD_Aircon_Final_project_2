package lk.Ijse.entity;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class ProductShowRoomEntity {
    private ProductsEntity products;
    private List<Product_ShowRoomEntity> psList;
}
