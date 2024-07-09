package lk.Ijse.BO.ShowroomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.dto.ShowRoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface ShowRoomBO extends SuperBO {
    public  boolean save(ShowRoomDTO showRoomDTO) throws SQLException, ClassNotFoundException ;


    public ShowRoom searchById(String id) throws SQLException, ClassNotFoundException ;


    public  boolean update(ShowRoomDTO showRoomDTO) throws SQLException, ClassNotFoundException ;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;


    public List<ShowRoomDTO> getAll() throws SQLException, ClassNotFoundException ;



    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException;}
