package lk.Ijse.repository;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepo {

    public static boolean save(Products product) throws SQLException {
        String sql = "INSERT INTO Product VALUES(?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, product.getProductId());
        pstm.setString(2, product.getProductDescription());
        pstm.setInt(3, product.getProductQuantity());
        pstm.setInt(4, product.getProductUnitPrice());

        return pstm.executeUpdate() > 0;
    }

    public static Products searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String productId = rs.getString("Product_id");
            String description = rs.getString("Product_Description");
            int QtyOnHand = rs.getInt("ShowRoom_QtyOnHand");
            int unitPrice = rs.getInt("Product_UnitPrice");

            Products product = new Products(productId, description,QtyOnHand,unitPrice);

            return product;
        }
        return null;
    }

    public static boolean update(Products product) throws SQLException {
        String sql = "UPDATE Product SET Product_Description = ?,ShowRoom_QtyOnHand = ?,Product_UnitPrice = ? WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, product.getProductDescription());
        pstm.setInt(2, product.getProductQuantity());
        pstm.setInt(3, product.getProductUnitPrice());
        pstm.setString(4, product.getProductId());
        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Product WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

  public static List<Products> getAll() throws SQLException {
        String sql = "SELECT * FROM Product";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Products> productList = new ArrayList<>();

        while (resultSet.next()) {
            String productId = resultSet.getString("Product_id");
            String description = resultSet.getString("Product_Description");
            int QtyOnHand = resultSet.getInt("ShowRoom_QtyOnHand");
            int unitPrice = resultSet.getInt("Product_UnitPrice");
            Products product = new Products(productId, description,QtyOnHand,unitPrice);
            productList.add(product);
        }
        return productList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT Product_id FROM Product";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("Product_id");
            idList.add(id);
        }
        return idList;
    }
   /* public static int getTotalQuantityOnHand() throws SQLException {
        String sql = "SELECT SUM(ShowRoom_QtyOnHand) AS TotalQuantity FROM Product";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TotalQuantity");
            } else {
                return 0; // Return 0 if no records found
            }
        }
    }
    public static List<Products> getAllJoined() throws SQLException {
        String sql = "SELECT p.Product_id, p.Product_Description, p.ShowRoom_QtyOnHand, p.Product_UnitPrice, ps.ShowRoom_id " +
                "FROM Product p " +
                "JOIN Product_ShowRoom ps ON p.Product_id = ps.Product_id";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<Products> productList = new ArrayList<>();

        while (resultSet.next()) {
            String productId = resultSet.getString("Product_id");
            String description = resultSet.getString("Product_Description");
            int qtyOnHand = resultSet.getInt("ShowRoom_QtyOnHand");
            int unitPrice = resultSet.getInt("Product_UnitPrice");
            String showRoomId = resultSet.getString("ShowRoom_id");

            Products product = new Products(productId, description, qtyOnHand, unitPrice);
           // product.ShowRoom(showRoomId); // Set the ShowRoomId for the product

            productList.add(product);
        }
        return productList;
    }*/

}
