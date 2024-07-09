package lk.Ijse.DAO;

import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.DAO.CustomerDAO.CustomerDAOImpl;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;
import lk.Ijse.DAO.LocationDAO.LocationDAOImpl;
import lk.Ijse.DAO.OrderDAO.OrderDAOImpl;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAOImpl;
import lk.Ijse.DAO.PaymentDAO.PaymentDAOImpl;
import lk.Ijse.DAO.ProductDAO.ProductDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAOImpl;
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;

public class DAOFactory {
private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
      Booking,Customer,Employee,Location,Order,OrderDetail,Payment,
        Product_ShowRoom,Products,ProductShowRoomJoin,Register,ShowRoom
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case Booking:
                return new BookingDAOImpl();
            case Customer:
                return new CustomerDAOImpl();
                case Employee:
                    return new EmployeeDAOImpl();
                    case Location:
                        return new LocationDAOImpl();
                        case Order:
                            return new OrderDAOImpl();
                            case OrderDetail:
                                return new OrderDetailDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
                    case Product_ShowRoom:
                        return new Product_ShowRoom_DAOImpl();
                        case Products:
                            return new ProductDAOImpl();
                            case ProductShowRoomJoin:
                                return new ProductShowRoomJoinDAOImpl();
                                case Register:
                                    return new RegisterDAOImpl();
            case ShowRoom:
                return new ShowRoomDAOImpl();
            default:
                return null;

        }
    }
}
