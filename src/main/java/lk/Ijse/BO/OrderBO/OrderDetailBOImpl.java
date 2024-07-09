package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAO;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAOImpl;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.OrderDetail);

    @Override
    public boolean save(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {
            boolean isSaved = saveOrderDetail(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean saveOrderDetail(OrderDetailDTO od) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.saveOrderDetail( new OrderDetail(od.getProductId(),
                od.getOrderId(),
                od.getQuantity(),
                od.getUnitPrice()));
    }
}
