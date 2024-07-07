package lk.Ijse.BO.OrderBO;

import lk.Ijse.Entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO {
          public  boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;


        public   boolean saveOrderDetail(OrderDetail od) throws SQLException, ClassNotFoundException;
}
