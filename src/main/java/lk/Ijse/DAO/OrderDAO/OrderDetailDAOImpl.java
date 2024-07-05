package lk.Ijse.DAO.OrderDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.OrderDetail;

import java.sql.PreparedStatement;
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
       /* String sql = "INSERT INTO OrderDetails (Product_id, Order_id, Qty, Product_UnitPrice) VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, od.getProductId());
        pstm.setString(2, od.getOrderId());
        pstm.setInt(3,    od.getQuantity());
        pstm.setDouble(4, od.getUnitPrice());

        return pstm.executeUpdate() > 0;*/

        return SqlUtil.execute("INSERT INTO OrderDetails (Product_id, Order_id, Qty, Product_UnitPrice) VALUES (?, ?, ?, ?)",
                od.getProductId(),
        od.getOrderId(),
        od.getQuantity(),
        od.getUnitPrice());
    }
}
