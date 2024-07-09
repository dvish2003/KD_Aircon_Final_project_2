package lk.Ijse.BO.ShowroomBO;

import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAO;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.dto.ShowRoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowRoomBOImpl implements ShowRoomBO {
ShowRoomDAO showRoomDAO = new ShowRoomDAOImpl();

    @Override
    public boolean save(ShowRoomDTO showRoomDTO) throws SQLException, ClassNotFoundException {
        return showRoomDAO.save(new ShowRoom( showRoomDTO.getShowRoomId(),
                showRoomDTO.getShowRoomLocation()));
    }

    @Override
    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException {
return showRoomDAO.searchById(id);    }

    @Override
    public boolean update(ShowRoomDTO showRoomDTO) throws SQLException, ClassNotFoundException {
        return showRoomDAO.update(new ShowRoom(
                showRoomDTO.getShowRoomId(),
                showRoomDTO.getShowRoomLocation()
               ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return showRoomDAO.delete(id);
    }

    @Override
    public List<ShowRoomDTO> getAll() throws SQLException, ClassNotFoundException {
List<ShowRoom> showRooms = showRoomDAO.getAll();
List<ShowRoomDTO> showRoomDTOList = new ArrayList<>();
for (ShowRoom showRoom : showRooms) {
    showRoomDTOList.add(new ShowRoomDTO(showRoom.getShowRoomId(),showRoom.getShowRoomLocation()));
}
return showRoomDTOList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> ids = showRoomDAO.getIds();
        List<String> list = new ArrayList<>();
        for (String id : ids) {
            list.add(id);
        }
        return list;    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return showRoomDAO.getCurrentId();
    }
}
