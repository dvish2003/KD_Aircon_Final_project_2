package lk.Ijse.BO.OrderBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {


    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }
}