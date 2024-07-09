package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.OrderDAO.OrderDAO;
import lk.Ijse.DAO.OrderDAO.OrderDAOImpl;
import lk.Ijse.Entity.Order;
import lk.Ijse.dto.OrderDTO;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Order);

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }

    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(
                orderDTO.getOrderId(),
                orderDTO.getCustomerId(),
                orderDTO.getPayment_Id(),
                orderDTO.getOrderPlaceDate()));
    }
}