package lk.Ijse.BO.LocationBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LocationBO extends SuperBO {
    public  boolean save(Location location) throws SQLException, ClassNotFoundException;


    public   Location searchById(String id) throws SQLException, ClassNotFoundException;


    public  boolean update(Location location) throws SQLException, ClassNotFoundException;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException;


    public List<Location> getAll() throws SQLException, ClassNotFoundException;


    public  List<String> getIds() throws SQLException, ClassNotFoundException;


    public  String getCurrentId() throws SQLException, ClassNotFoundException;

    }
