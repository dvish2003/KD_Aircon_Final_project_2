package lk.Ijse.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class PaymentDTO {
    private String paymentId;
    private int paymentAmount;
    private Date paymentDate;


}
