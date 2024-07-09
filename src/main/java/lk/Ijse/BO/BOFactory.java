package lk.Ijse.BO;

import lk.Ijse.BO.BookingBO.BookingBOImpl;
import lk.Ijse.BO.CustomerBO.CustomerBOImpl;
import lk.Ijse.BO.EmployeeBO.EmployeeBOImpl;
import lk.Ijse.BO.LocationBO.LocationBOImpl;
import lk.Ijse.BO.OrderBO.OrderBOImpl;
import lk.Ijse.BO.OrderBO.OrderDetailBOImpl;
import lk.Ijse.BO.PaymentBO.PaymentBOImpl;
import lk.Ijse.BO.ProductBO.ProductBOImpl;
import lk.Ijse.BO.ProductShowroomBO.ProductShowRoomJoinBOImpl;
import lk.Ijse.BO.ProductShowroomBO.Product_ShowRoom_BOImpl;
import lk.Ijse.BO.RegisterBO.RegisterBOImpl;
import lk.Ijse.BO.ShowroomBO.ShowRoomBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
private BOFactory() {

}

public static BOFactory getBoFactory() {
    return (boFactory == null) ? boFactory = new BOFactory() : boFactory;

}
public enum BoType{
    Booking,Customer,Employee,Location,Order,OrderDetail,Payment,
    Product_ShowRoom,Products,ProductShowRoomJoin,Register,ShowRoom
}
    public SuperBO getBo(BoType boType){
        switch (boType){
            case Booking:
                return new BookingBOImpl();
            case Customer:
                return new CustomerBOImpl();
            case Employee:
                return new EmployeeBOImpl();
            case Location:
                return new LocationBOImpl();
            case Order:
                return new OrderBOImpl();
            case OrderDetail:
                return new OrderDetailBOImpl();
            case Payment:
                return new PaymentBOImpl();
            case Product_ShowRoom:
                return new Product_ShowRoom_BOImpl();
            case Products:
                return new ProductBOImpl();
            case ProductShowRoomJoin:
                return new ProductShowRoomJoinBOImpl();
            case Register:
                return new RegisterBOImpl();
            case ShowRoom:
                return new ShowRoomBOImpl();
            default:
                return null;

        }
    }
}
