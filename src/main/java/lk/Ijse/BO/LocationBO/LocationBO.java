package lk.Ijse.BO.LocationBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Location;
import lk.Ijse.dto.LocationDTO;

import java.sql.SQLException;
import java.util.List;

public interface LocationBO extends SuperBO {
    public  boolean save(LocationDTO locationDTO) throws SQLException, ClassNotFoundException;


    public Location searchById(String id) throws SQLException, ClassNotFoundException;


    public  boolean update(LocationDTO locationDTO) throws SQLException, ClassNotFoundException;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException;


    public List<LocationDTO> getAll() throws SQLException, ClassNotFoundException;


    public  List<String> getIds() throws SQLException, ClassNotFoundException;


    public  String getCurrentId() throws SQLException, ClassNotFoundException;

    }
