package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    public  String getCurrentId() throws SQLException, ClassNotFoundException {
/*
        String sql = "SELECT Order_id FROM `Order` ORDER BY Order_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
*/

        ResultSet resultSet = SqlUtil.execute("SELECT Order_id FROM `Order` ORDER BY Order_id DESC LIMIT 1");
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }
   /* public  String getPayCurrentId() throws SQLException, ClassNotFoundException {
        *//*String sql = "SELECT Payment_id FROM Payment ORDER BY Payment_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*//*

        ResultSet resultSet = SqlUtil.execute( "SELECT Payment_id FROM Payment ORDER BY Payment_id DESC LIMIT 1");
        if(resultSet.next()) {
            String PayId = resultSet.getString(1);
            return PayId;
        }
        return null;
    }*/
    public  boolean save(Order order) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO `Order` VALUES(?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOrderId());
        pstm.setString(2, order.getCustomerId());
        pstm.setString(3, order.getPayment_Id());
        pstm.setDate(4,   order.getOrderPlaceDate());

        return pstm.executeUpdate() > 0;*/

        return SqlUtil.execute("INSERT INTO `Order` VALUES(?, ?, ?,?)",
                order.getOrderId(),
                order.getCustomerId(),
                order.getPayment_Id(),
                order.getOrderPlaceDate());
    }

}