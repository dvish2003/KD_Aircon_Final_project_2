package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Order;
import lk.Ijse.Entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    public  boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;


      boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException;

}
