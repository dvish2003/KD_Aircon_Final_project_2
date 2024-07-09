package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO {
    private OrderDTO orderDTO;
    private List<OrderDetailDTO> odList;
    private PaymentDTO paymentDTO;
}
