package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.ProductShowRoomJoin;

import java.sql.SQLException;
import java.util.List;

public interface ProductShowRoomJoinBO extends SuperBO {
    public List<ProductShowRoomJoin> getAll() throws SQLException, ClassNotFoundException ;
}
