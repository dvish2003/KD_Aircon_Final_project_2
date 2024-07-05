package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Order;
import lk.Ijse.Model.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    public  boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;


      boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException;

}
