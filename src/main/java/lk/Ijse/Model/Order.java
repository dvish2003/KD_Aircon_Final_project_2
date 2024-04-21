package lk.Ijse.Model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Order {
    private String orderId;
  //  public CustomerRepo id;
  //  public PaymentRepo paymentId;
   // private String status;
    private Date OrderPlaceDate;
  //  private Date handOverdate;


}
