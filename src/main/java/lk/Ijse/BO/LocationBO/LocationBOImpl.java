package lk.Ijse.BO.LocationBO;

import lk.Ijse.DAO.LocationDAO.LocationDAO;
import lk.Ijse.DAO.LocationDAO.LocationDAOImpl;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationBOImpl implements LocationBO {

    LocationDAO locationDAO = new LocationDAOImpl();
    @Override
    public boolean save(Location location) throws SQLException, ClassNotFoundException {
        return locationDAO.save(new Location
                (location.getCustomerId(),
                location.getId(),
                location.getProvince(),
                location.getCity(),
                location.getAddress(),
                location.getZipCode()));
    }

    @Override
    public Location searchById(String id) throws SQLException, ClassNotFoundException {
        return locationDAO.searchById(id);
    }

    @Override
    public boolean update(Location location) throws SQLException, ClassNotFoundException {
        return locationDAO.update(new Location(location.getCustomerId(),
                location.getProvince(),
                location.getCity(),
                location.getAddress(),
                location.getZipCode(),
                location.getId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return locationDAO.delete(id);
    }

    @Override
    public List<Location> getAll() throws SQLException, ClassNotFoundException {
    List<Location> locations = locationDAO.getAll();
    return locations;
        }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
List<String> ids = locationDAO.getIds();
List<String> list = new ArrayList<>();
for (String id : ids) {
    list.add(id);
}
return list;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return locationDAO.getCurrentId();
    }
}