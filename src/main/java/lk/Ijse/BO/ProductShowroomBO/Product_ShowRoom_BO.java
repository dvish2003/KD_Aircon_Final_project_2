package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Product_ShowRoom;

import java.sql.SQLException;
import java.util.List;

public interface Product_ShowRoom_BO extends SuperBO {
        public  boolean save(Product_ShowRoom ps) throws SQLException, ClassNotFoundException;

        public List<Product_ShowRoom> getAll() throws SQLException, ClassNotFoundException ;
}
