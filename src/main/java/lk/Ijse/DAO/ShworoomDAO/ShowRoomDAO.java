package lk.Ijse.DAO.ShworoomDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Model.Register;
import lk.Ijse.Model.ShowRoom;

import java.sql.SQLException;

public interface ShowRoomDAO extends CrudDAO <ShowRoom> {
    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException;
}
