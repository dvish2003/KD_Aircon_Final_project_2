package lk.Ijse.repository;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.PlaceOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);


            PreparedStatement stmPayment = connection.prepareStatement("INSERT INTO Payment VALUES (?, ?, ?)");
            stmPayment.setObject(1, placeOrder.getPayment().getPaymentId());
            stmPayment.setObject(2, placeOrder.getPayment().getPaymentAmount());
            stmPayment.setObject(3, placeOrder.getPayment().getPaymentDate());
            int affectedRowsPayment = stmPayment.executeUpdate();

            PreparedStatement stmOrder = connection.prepareStatement("INSERT INTO `Order` VALUES (?, ?, ?, ?)");
            stmOrder.setObject(1, placeOrder.getOrder().getOrderId());
            stmOrder.setObject(2, placeOrder.getOrder().getCustomerId());
            stmOrder.setObject(3, placeOrder.getOrder().getPayment_Id());
            stmOrder.setObject(4, placeOrder.getOrder().getOrderPlaceDate());
            int affectedRowsOrder = stmOrder.executeUpdate();

            PreparedStatement stmOrderDetail = connection.prepareStatement("INSERT INTO OrderDetails VALUES (?, ?, ?, ?)");
            for (var orderDetail : placeOrder.getOdList()) {
                stmOrderDetail.setObject(1, orderDetail.getProductId());
                stmOrderDetail.setObject(2, orderDetail.getOrderId());
                stmOrderDetail.setObject(3, orderDetail.getQuantity());
                stmOrderDetail.setObject(4, orderDetail.getUnitPrice());
                stmOrderDetail.addBatch();
            }
            int[] affectedRowsOrderDetail = stmOrderDetail.executeBatch();

            connection.commit();
            return affectedRowsOrder > 0 && affectedRowsPayment > 0 && affectedRowsOrderDetail.length > 0;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
