package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SuperDAO;
import lk.Ijse.Entity.Order;
import lk.Ijse.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends SuperDAO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException;

 //   public  String getPayCurrentId() throws SQLException, ClassNotFoundException;

    public  boolean save(Order order) throws SQLException, ClassNotFoundException;

    boolean update(Order Order) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<Order> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}
