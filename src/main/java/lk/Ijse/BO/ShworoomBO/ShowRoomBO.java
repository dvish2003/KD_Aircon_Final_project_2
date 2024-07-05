package lk.Ijse.BO.ShworoomBO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Model.ShowRoom;

import java.sql.SQLException;

public interface ShowRoomBO extends CrudDAO <ShowRoom> {
    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException;
}
