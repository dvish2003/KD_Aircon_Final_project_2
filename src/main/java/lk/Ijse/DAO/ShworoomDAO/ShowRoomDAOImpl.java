package lk.Ijse.DAO.ShworoomDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.dto.ShowRoomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowRoomDAOImpl implements ShowRoomDAO {

    public  boolean save(ShowRoom showRoom) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO ShowRoom (ShowRoom_id, ShowRoom_Location) VALUES (?, ?)",
                showRoom.getShowRoomId(),
                showRoom.getShowRoomLocation());
    }

    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs =SqlUtil.execute("SELECT * FROM ShowRoom WHERE ShowRoom_id = ?",id);
        if (rs.next()) {
            String showRoomId = rs.getString("ShowRoom_id");
            String location = rs.getString("ShowRoom_Location");


            ShowRoom showRoom = new ShowRoom(showRoomId, location);

            return showRoom;
        }
        return null;
    }

    public  boolean update(ShowRoom showRoom) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE ShowRoom SET ShowRoom_Location = ? WHERE ShowRoom_id = ?",
                  showRoom.getShowRoomLocation(),
                  showRoom.getShowRoomId());

    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM ShowRoom WHERE ShowRoom_id = ?",id);
    }

    public  List<ShowRoom> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM ShowRoom");

        List<ShowRoom> showRoomList = new ArrayList<>();

        while (resultSet.next()) {
            String showRoomId = resultSet.getString("ShowRoom_id");
            String location = resultSet.getString("ShowRoom_Location");
            ShowRoom showRoom = new ShowRoom(showRoomId, location);
            showRoomList.add(showRoom);
        }
        return showRoomList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute("SELECT ShowRoom_id FROM ShowRoom");
        while (resultSet.next()) {
            String id = resultSet.getString("ShowRoom_id");
            idList.add(id);
        }
        return idList;
    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.execute("SELECT ShowRoom_id FROM ShowRoom ORDER BY ShowRoom_id DESC LIMIT 1");
        if(resultSet.next()) {
            String ShowRoom_id = resultSet.getString(1);
            return ShowRoom_id;
        }
        return null;
    }
}
