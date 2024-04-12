package lk.Ijse.repository;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepo {
    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers VALUES(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customer.getId());
        pstm.setObject(2,customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4,customer.getContact());
        pstm.setObject(5,customer.getEmail());

           return pstm.executeUpdate() > 0;
    }
    public static Customer searchById(String id) throws SQLException {
 String sql = "SELECT * FROM Customers WHERE id = ?";
 Connection connection = DbConnection.getInstance().getConnection();
 PreparedStatement pstm = connection.prepareStatement(sql);
 pstm.setObject(1,id);
 ResultSet rs = pstm.executeQuery();
 if (rs.next()){
     String cus_id = rs.getString(1);
     String Name = rs.getString(2);
     String Address = rs.getString(3);
     int Contact = rs.getInt(4);
     String Email = rs.getString(5);

     Customer customer = new Customer(cus_id,Name,Address,Contact,Email);

      return customer;

 }
 return null;

    }
    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET Name = ?, Address = ?, Contact = ?,Email = ? WHERE id = ?";
Connection connection = DbConnection.getInstance().getConnection();
PreparedStatement pstm = connection.prepareStatement(sql);
pstm.setObject(1,customer.getName());
pstm.setObject(2,customer.getAddress());
pstm.setObject(3,customer.getContact());
pstm.setObject(4,customer.getEmail());
pstm.setObject(5,customer.getId());
return pstm.executeUpdate() > 0;



    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
}




