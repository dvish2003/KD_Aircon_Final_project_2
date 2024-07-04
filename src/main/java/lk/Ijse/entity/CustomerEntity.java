package lk.Ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CustomerEntity {
    private String id;
    private String Name;
    private String Address;
    private String Contact;
    private String email;


    public CustomerEntity(String cuId) {
        this.id = cuId;
    }
}
