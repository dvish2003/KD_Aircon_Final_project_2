package lk.Ijse.BO.ShworoomBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.ShowRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowRoomBOImpl implements ShowRoomBO {


    @Override
    public boolean save(ShowRoom showRoom) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ShowRoom showRoom) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ShowRoom> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
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
