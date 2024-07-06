package lk.Ijse.BO.LocationBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationBOImpl implements LocationBO {

    @Override
    public boolean save(Location location) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Location searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Location location) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Location> getAll() throws SQLException, ClassNotFoundException {
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