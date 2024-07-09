package lk.Ijse.DAO.ShworoomDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.dto.ShowRoomDTO;

import java.sql.SQLException;

public interface ShowRoomDAO extends CrudDAO <ShowRoom> {
    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException;
}
