package lk.Ijse.Model;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class Booking {
    private String bookingId;
    //public CustomerRepo id;
  //  public EmployeeRepo empId;
   // public LocationRepo Locid;
  //  public PaymentRepo paymentId;
    private Date bookingDate;
    private String bookingTime;
    private String bookingDescription;


}
