package lk.Ijse.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class PaymentEntity {
    private String paymentId;
    private int paymentAmount;
    private Date paymentDate;


}
