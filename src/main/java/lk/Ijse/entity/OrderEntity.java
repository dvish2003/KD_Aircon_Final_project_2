package lk.Ijse.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class OrderEntity {
     private String orderId;
     private String customerId;
     private String payment_Id;
     private Date OrderPlaceDate;



}
