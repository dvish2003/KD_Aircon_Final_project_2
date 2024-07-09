package lk.Ijse.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class ProductShowRoom_StDTO {
    private ProductsDTO productsDTO;
    private List<Product_ShowRoomDTO> psList;
}
