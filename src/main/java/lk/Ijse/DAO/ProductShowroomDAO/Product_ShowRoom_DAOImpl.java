package lk.Ijse.DAO.ProductShowroomDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Product_ShowRoom;
import lk.Ijse.dto.Product_ShowRoomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_ShowRoom_DAOImpl implements Product_ShowRoom_DAO {

    public  boolean save(Product_ShowRoom ps) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Product_ShowRoom  VALUES(?,?)",
                ps.getProductID(),
                ps.getShowRoomId());

        }

    @Override
    public boolean update(Product_ShowRoom dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  List<Product_ShowRoom> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Product_ShowRoom");

        List<Product_ShowRoom> PSList = new ArrayList<>();

        while (resultSet.next()) {
            String P_ID = resultSet.getString(2);
            String S_Id = resultSet.getString(1);

            Product_ShowRoom ps = new Product_ShowRoom(P_ID,S_Id);
            PSList.add(ps);
        }
        return PSList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }
}

