package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderEntity {
    private OrderEntity order;
    private List<OrderDetailEntity> odList;
    private PaymentEntity payment;
}
