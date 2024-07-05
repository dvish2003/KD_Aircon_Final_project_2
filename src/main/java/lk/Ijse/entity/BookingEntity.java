package lk.Ijse.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class BookingEntity {
    private String bookingId;
    private String empId;
    private String LocId;
    private String paymentId;
    private Date bookingDate;
    private Date PlaceDate;
    private String bookingDescription;


}
