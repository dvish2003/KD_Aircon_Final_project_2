package lk.Ijse.BO.ProductBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Model.OrderDetail;
import lk.Ijse.Model.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    public  boolean save(Products product) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO Product VALUES(?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, product.getProduct_id());
        pstm.setString(2, product.getProduct_description());
        pstm.setInt(3,    product.getShowRoom_qtyOnHand());
        pstm.setInt(4,    product.getProduct_unitPrice());


        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute( "INSERT INTO Product VALUES(?,?,?,?)",
                product.getProduct_id(),
                product.getProduct_description(),
                product.getShowRoom_qtyOnHand(),
                product.getProduct_unitPrice());

    }
    public  Products searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM Product WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);*/
        ResultSet rs = SqlUtil.execute( "SELECT * FROM Product WHERE Product_id = ?",id);
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
    public  Products searchByName(String Description) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM Product WHERE Product_Description = ?";
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, Description);
        ResultSet rs = SqlUtil.execute( "SELECT * FROM Product WHERE Product_Description = ?",Description);
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
    public  boolean update(Products product) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE Product SET Product_Description = ?, ShowRoom_QtyOnHand = ShowRoom_QtyOnHand + ?, Product_UnitPrice = ? WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, product.getProduct_description());
        pstm.setInt(2,     product.getShowRoom_qtyOnHand());
        pstm.setInt(3,      product.getProduct_unitPrice());
        pstm.setString(4,          product.getProduct_id());
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("UPDATE Product SET Product_Description = ?, ShowRoom_QtyOnHand = ShowRoom_QtyOnHand + ?, Product_UnitPrice = ? WHERE Product_id = ?",
                product.getProduct_description(),
                product.getShowRoom_qtyOnHand(),
                product.getProduct_unitPrice(),
                product.getProduct_id());
    }
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "DELETE FROM Product WHERE Product_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
*/
        return SqlUtil.execute("DELETE FROM Product WHERE Product_id = ?",id);
    }
    public  List<Products> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Product";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        ResultSet resultSet = SqlUtil.execute( "SELECT * FROM Product");

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
    public  List<String> getIds() throws SQLException, ClassNotFoundException {
     /*   String sql = "SELECT Product_id FROM Product";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute( "SELECT Product_id FROM Product");
        while (resultSet.next()) {
            String id = resultSet.getString("Product_id");
            idList.add(id);
        }
        return idList;
    }
    public  List<String> getNames() throws SQLException, ClassNotFoundException  {
        /*String sql = "SELECT Product_Description FROM Product";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute( "SELECT Product_Description FROM Product");
        while (resultSet.next()) {
            String Name = resultSet.getString("product_description");
            idList.add(Name);
        }
        return idList;
    }
    public  boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getProductId(), od.getQuantity());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    public  boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE Product SET ShowRoom_QtyOnHand = ShowRoom_QtyOnHand - ? WHERE Product_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, Id);
*/
        return SqlUtil.execute( "UPDATE Product SET ShowRoom_QtyOnHand = ShowRoom_QtyOnHand - ? WHERE Product_id = ?",qty,Id);
    }
    public  String  getCurrentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT Product_id FROM Product ORDER BY Product_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
*/
        ResultSet resultSet = SqlUtil.execute("SELECT Product_id FROM Product ORDER BY Product_id DESC LIMIT 1");
        if(resultSet.next()) {
            String Product_id = resultSet.getString(1);
            return Product_id;
        }
        return null;
    }

}
