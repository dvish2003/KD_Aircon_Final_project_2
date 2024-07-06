package lk.Ijse.DAO.ShworoomDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.ShowRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowRoomDAOImpl implements ShowRoomDAO {

    public  boolean save(ShowRoom showRoom) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO ShowRoom (ShowRoom_id, ShowRoom_Location) VALUES (?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, showRoom.getShowRoomId());
        pstm.setString(2, showRoom.getShowRoomLocation());


        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("INSERT INTO ShowRoom (ShowRoom_id, ShowRoom_Location) VALUES (?, ?)",
                showRoom.getShowRoomId(),
                showRoom.getShowRoomLocation());
    }

    public  ShowRoom searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM ShowRoom WHERE ShowRoom_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);*/
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
       /* String sql = "UPDATE ShowRoom SET ShowRoom_Location = ? WHERE ShowRoom_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, showRoom.getShowRoomLocation());
        pstm.setString(2, showRoom.getShowRoomId());
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("UPDATE ShowRoom SET ShowRoom_Location = ? WHERE ShowRoom_id = ?",
                  showRoom.getShowRoomLocation(),
                  showRoom.getShowRoomId());

    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM ShowRoom WHERE ShowRoom_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("DELETE FROM ShowRoom WHERE ShowRoom_id = ?",id);
    }

    public  List<ShowRoom> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM ShowRoom";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
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
       /* String sql = "SELECT ShowRoom_id FROM ShowRoom";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute("SELECT ShowRoom_id FROM ShowRoom");
        while (resultSet.next()) {
            String id = resultSet.getString("ShowRoom_id");
            idList.add(id);
        }
        return idList;
    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT ShowRoom_id FROM ShowRoom ORDER BY ShowRoom_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

        ResultSet resultSet = SqlUtil.execute("SELECT ShowRoom_id FROM ShowRoom ORDER BY ShowRoom_id DESC LIMIT 1");
        if(resultSet.next()) {
            String ShowRoom_id = resultSet.getString(1);
            return ShowRoom_id;
        }
        return null;
    }
}
