package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {


    @Override
    public boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException {
        return false;
    }
}
