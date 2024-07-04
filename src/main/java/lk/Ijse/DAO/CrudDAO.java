package lk.Ijse.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T>{
    public  boolean save(T dto) throws SQLException, ClassNotFoundException;

    public  boolean update(T dto) throws SQLException, ClassNotFoundException;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  List<T> getAll() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException;

    public  String getCurrentId() throws SQLException, ClassNotFoundException;



}
