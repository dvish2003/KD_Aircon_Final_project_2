package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;

 //   public  String getPayCurrentId() throws SQLException, ClassNotFoundException;

    public  boolean save(Order order) throws SQLException, ClassNotFoundException;

}
