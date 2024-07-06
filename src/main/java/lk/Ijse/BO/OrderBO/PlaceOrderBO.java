package lk.Ijse.BO.OrderBO;

import lk.Ijse.BO.BookingBO.BookingBO;
import lk.Ijse.BO.BookingBO.BookingBOImpl;
import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.CustomerBO.CustomerBOImpl;
import lk.Ijse.BO.EmployeeBO.EmployeeBO;
import lk.Ijse.BO.EmployeeBO.EmployeeBOImpl;
import lk.Ijse.BO.LocationBO.LocationBO;
import lk.Ijse.BO.LocationBO.LocationBOImpl;
import lk.Ijse.BO.PaymentBO.PaymentBO;
import lk.Ijse.BO.PaymentBO.PaymentBOImpl;
import lk.Ijse.BO.ProductBO.ProductBO;
import lk.Ijse.BO.ProductBO.ProductBOImpl;
import lk.Ijse.BO.ProductShowroomBO.ProductShowRoomJoinBO;
import lk.Ijse.BO.ProductShowroomBO.ProductShowRoomJoinBOImpl;
import lk.Ijse.BO.ProductShowroomBO.Product_ShowRoom_BO;
import lk.Ijse.BO.ProductShowroomBO.Product_ShowRoom_BOImpl;
import lk.Ijse.BO.RegisterBO.RegisterBO;
import lk.Ijse.BO.RegisterBO.RegisterBOImpl;
import lk.Ijse.BO.ShworoomBO.ShowRoomBO;
import lk.Ijse.BO.ShworoomBO.ShowRoomBOImpl;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBO {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {

        EmployeeBO employeeDAO = new EmployeeBOImpl();
        BookingBO bookingDAO = new BookingBOImpl();
        CustomerBO customerDAO = new CustomerBOImpl();
        LocationBO locationBO = new LocationBOImpl();
        OrderBO orderBO = new OrderBOImpl();
        OrderDetailBO orderDetailBO = new OrderDetailBOImpl();
        RegisterBO registerBO = new RegisterBOImpl();
        ShowRoomBO showRoomBO = new ShowRoomBOImpl();
        ProductShowRoomJoinBO productShowRoomJoinBO = new ProductShowRoomJoinBOImpl();
        Product_ShowRoom_BO productShowRoomDao = new Product_ShowRoom_BOImpl();
        PaymentBO paymentBO = new PaymentBOImpl();
        ProductBO productBO = new ProductBOImpl();

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPayUpdated = paymentBO.save(po.getPayment());
            if (isPayUpdated) {
            boolean isOrderSaved = orderBO.save(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = productBO.update1(po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = orderDetailBO.save(po.getOdList());
                    if (isOrderDetailSaved) {

                        connection.commit();
                        return true;
                    }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
