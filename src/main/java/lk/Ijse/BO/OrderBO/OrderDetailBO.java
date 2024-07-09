package lk.Ijse.BO.OrderBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
          public  boolean save(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;


        public   boolean saveOrderDetail(OrderDetailDTO od) throws SQLException, ClassNotFoundException;
}
