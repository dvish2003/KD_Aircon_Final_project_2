package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public  boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isSaved = saveOrderDetail(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    public   boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("INSERT INTO OrderDetails (Product_id, Order_id, Qty, Product_UnitPrice) VALUES (?, ?, ?, ?)",
                od.getProductId(),
                od.getOrderId(),
                od.getQuantity(),
                od.getUnitPrice());
    }
}
