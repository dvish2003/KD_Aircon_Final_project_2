package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.dto.Product_ShowRoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface Product_ShowRoom_BO extends SuperBO {
        public  boolean save(Product_ShowRoomDTO ps) throws SQLException, ClassNotFoundException;

        public List<Product_ShowRoomDTO> getAll() throws SQLException, ClassNotFoundException ;
}
