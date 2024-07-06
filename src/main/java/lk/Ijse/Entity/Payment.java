package lk.Ijse.Entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Payment {
    private String paymentId;
    private int paymentAmount;
    private Date paymentDate;


}
