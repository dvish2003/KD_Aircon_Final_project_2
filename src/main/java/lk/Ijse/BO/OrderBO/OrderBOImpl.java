package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.OrderDAO.OrderDAO;
import lk.Ijse.DAO.OrderDAO.OrderDAOImpl;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(
                order.getOrderId(),
                order.getCustomerId(),
                order.getPayment_Id(),
                order.getOrderPlaceDate()));
    }
}