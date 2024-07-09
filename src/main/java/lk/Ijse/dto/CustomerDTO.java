package lk.Ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CustomerDTO {
    private String id;
    private String Name;
    private String Address;
    private String Contact;
    private String email;


    public CustomerDTO(String cuId) {
        this.id = cuId;
    }
}
