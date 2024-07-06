package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Product_ShowRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_ShowRoom_BOImpl implements Product_ShowRoom_BO {


    @Override
    public boolean save(Product_ShowRoom ps) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Product_ShowRoom> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}

