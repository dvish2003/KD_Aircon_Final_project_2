package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.dto.ProductShowRoomJoinDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductShowRoomJoinBO extends SuperBO {
    public List<ProductShowRoomJoinDTO> getAll() throws SQLException, ClassNotFoundException ;
}
