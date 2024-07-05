package lk.Ijse.BO.OrderBO;

import lk.Ijse.Model.Order;

import java.sql.SQLException;

public interface OrderBO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;

 //   public  String getPayCurrentId() throws SQLException, ClassNotFoundException;

    public  boolean save(Order order) throws SQLException, ClassNotFoundException;

}
