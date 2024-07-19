package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAO;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAOImpl;
import lk.Ijse.Entity.Product_ShowRoom;
import lk.Ijse.dto.Product_ShowRoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_ShowRoom_BOImpl implements Product_ShowRoom_BO {

    Product_ShowRoom_DAOImpl psd = (Product_ShowRoom_DAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Product_ShowRoom);
    @Override
    public boolean save(Product_ShowRoomDTO ps) throws SQLException, ClassNotFoundException {
        return psd.save(new Product_ShowRoom(
                ps.getProductID(),
                ps.getShowRoomId()));
    }

    @Override
    public List<Product_ShowRoomDTO> getAll() throws SQLException, ClassNotFoundException {
List<Product_ShowRoom> list = psd.getAll();
    List<Product_ShowRoomDTO> dtos = new ArrayList<>();
    for (Product_ShowRoom p : list) {
    dtos.add(new Product_ShowRoomDTO(p.getProductID(), p.getShowRoomId()));
    }
    return dtos;
    }
}


