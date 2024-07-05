package lk.Ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data


public class LocationEntity {

    private String customerId;
    private String id;
    private String province;
    private String city;
    private String Address;
    private String zipCode;


}
