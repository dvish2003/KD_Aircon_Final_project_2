package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.CustomerBO.CustomerBOImpl;
import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAO;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;
import lk.Ijse.DAO.LocationDAO.LocationDAO;
import lk.Ijse.DAO.LocationDAO.LocationDAOImpl;
import lk.Ijse.DAO.PaymentDAO.PaymentDAO;
import lk.Ijse.DAO.PaymentDAO.PaymentDAOImpl;
import lk.Ijse.DAO.ProductDAO.ProductDAO;
import lk.Ijse.DAO.ProductDAO.ProductDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAO;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAO;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAOImpl;
import lk.Ijse.DAO.RegisterDAO.RegisterDAO;
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAO;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderDAO {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        BookingDAO bookingDAO = new BookingDAOImpl();
        CustomerBO customerDAO = new CustomerBOImpl();
        LocationDAO locationDAO = new LocationDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
        RegisterDAO registerDAO = new RegisterDAOImpl();
        ShowRoomDAO showRoomDAO = new ShowRoomDAOImpl();
        ProductShowRoomJoinDAO productShowRoomJoinDAO = new ProductShowRoomJoinDAOImpl();
        Product_ShowRoom_DAO productShowRoomDao = new Product_ShowRoom_DAOImpl();
        PaymentDAO paymentDAO = new PaymentDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPayUpdated = paymentDAO.save(po.getPayment());
            if (isPayUpdated) {
            boolean isOrderSaved = orderDAO.save(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = productDAO.update1(po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = orderDetailDAO.save(po.getOdList());
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
