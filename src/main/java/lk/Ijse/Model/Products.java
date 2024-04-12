package lk.Ijse.Model;

      public class Products {
            private String productId;
            private String productDescription;
            private int productUnitPrice;


            public Products() {
            }

            public Products(String productId, String productDescription, int productUnitPrice) {
                  this.productId = productId;
                  this.productDescription = productDescription;
                  this.productUnitPrice = productUnitPrice;
            }

            public String getProductId() {
                  return productId;
            }

            public void setProductId(String productId) {
                  this.productId = productId;
            }

            public String getProductDescription() {
                  return productDescription;
            }

            public void setProductDescription(String productDescription) {
                  this.productDescription = productDescription;
            }

            public int getProductUnitPrice() {
                  return productUnitPrice;
            }

            public void setProductUnitPrice(int productUnitPrice) {
                  this.productUnitPrice = productUnitPrice;
            }
            @Override
            public String toString() {
                  return "ProductsRepo{" +
                          "productId='" + productId + '\'' +
                          ", productDescription='" + productDescription + '\'' +
                          ", productUnitPrice=" + productUnitPrice +
                          '}';
            }
      }
