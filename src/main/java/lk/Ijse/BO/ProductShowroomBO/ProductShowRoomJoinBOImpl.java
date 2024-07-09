package lk.Ijse.BO.ProductShowroomBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAO;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAOImpl;
import lk.Ijse.Entity.ProductShowRoomJoin;
import lk.Ijse.dto.ProductShowRoomJoinDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductShowRoomJoinBOImpl implements ProductShowRoomJoinBO {
ProductShowRoomJoinDAO productShowRoomJoinDAO = (ProductShowRoomJoinDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ProductShowRoomJoin);
    @Override
    public List<ProductShowRoomJoinDTO> getAll() throws SQLException, ClassNotFoundException {
    List<ProductShowRoomJoin> productShowRoomJoins = productShowRoomJoinDAO.getAll();
    List<ProductShowRoomJoinDTO> productShowRoomJoinDTOS = new ArrayList<>();
    for (ProductShowRoomJoin p: productShowRoomJoins) {
    productShowRoomJoinDTOS.add(new ProductShowRoomJoinDTO(
            p.getShowRoomId(),
            p.getProductId(),
            p.getProductDescription(),
            p.getProductQuantityOnHand(),
            p.getProductUnitPrice()));
}
return productShowRoomJoinDTOS;
    }
}
