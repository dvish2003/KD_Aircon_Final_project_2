package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Order;
import lk.Ijse.dto.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SqlUtil.execute("SELECT Order_id FROM `Order` ORDER BY Order_id DESC LIMIT 1");
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public  boolean save(Order order) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("INSERT INTO `Order` VALUES(?, ?, ?,?)",
                order.getOrderId(),
                order.getCustomerId(),
                order.getPayment_Id(),
                order.getOrderPlaceDate());
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

}