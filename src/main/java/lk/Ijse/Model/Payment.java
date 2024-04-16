package lk.Ijse.Model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private int paymentAmount;
    private Date paymentDate;


}
