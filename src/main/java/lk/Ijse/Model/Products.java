package lk.Ijse.Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

      public class Products {
            private String productId;
            private String productDescription;
      private int productQuantity;
      private int productUnitPrice;


      public void ShowRoom(String showRoomId) {
      }
}
