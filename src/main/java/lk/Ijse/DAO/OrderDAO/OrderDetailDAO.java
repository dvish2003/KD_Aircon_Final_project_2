package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SuperDAO;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends SuperDAO {
    public  boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;


      boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException;

}
