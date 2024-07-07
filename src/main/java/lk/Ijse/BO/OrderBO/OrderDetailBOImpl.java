package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.OrderDAO.OrderDetailDAO;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAOImpl;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isSaved = saveOrderDetail(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.saveOrderDetail( new OrderDetail(od.getProductId(),
                od.getOrderId(),
                od.getQuantity(),
                od.getUnitPrice()));
    }
}
