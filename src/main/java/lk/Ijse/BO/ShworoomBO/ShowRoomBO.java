package lk.Ijse.BO.ShworoomBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.ShowRoom;

import java.sql.SQLException;
import java.util.List;

public interface ShowRoomBO extends SuperBO {
    public  boolean save(ShowRoom showRoom) throws SQLException, ClassNotFoundException ;


    public  ShowRoom searchById(String id) throws SQLException, ClassNotFoundException ;


    public  boolean update(ShowRoom showRoom) throws SQLException, ClassNotFoundException ;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;


    public List<ShowRoom> getAll() throws SQLException, ClassNotFoundException ;



    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException;}
