package lk.Ijse.BO.OrderBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
          public  String getCurrentId() throws SQLException, ClassNotFoundException;

        public  boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

}
