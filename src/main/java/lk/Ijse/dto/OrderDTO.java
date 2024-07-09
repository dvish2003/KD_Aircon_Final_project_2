package lk.Ijse.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class OrderDTO {
     private String orderId;
     private String customerId;
     private String payment_Id;
     private Date OrderPlaceDate;



}
