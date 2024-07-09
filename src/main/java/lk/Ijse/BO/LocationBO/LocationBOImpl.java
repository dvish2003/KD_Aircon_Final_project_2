package lk.Ijse.BO.LocationBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.LocationDAO.LocationDAO;
import lk.Ijse.DAO.LocationDAO.LocationDAOImpl;
import lk.Ijse.Entity.Location;
import lk.Ijse.dto.LocationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationBOImpl implements LocationBO {

    LocationDAO locationDAO = (LocationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Location);
    @Override
    public boolean save(LocationDTO locationDTO) throws SQLException, ClassNotFoundException {
        return locationDAO.save(new Location
                (locationDTO.getCustomerId(),
                locationDTO.getId(),
                locationDTO.getProvince(),
                locationDTO.getCity(),
                locationDTO.getAddress(),
                locationDTO.getZipCode()));
    }

    @Override
    public Location searchById(String id) throws SQLException, ClassNotFoundException {
return locationDAO.searchById(id);
    }
    @Override
    public boolean update(LocationDTO location) throws SQLException, ClassNotFoundException {
        return locationDAO.update(new Location(
                location.getCustomerId(),
                location.getId(),
                location.getProvince(),
                location.getCity(),
                location.getAddress(),
                location.getZipCode()
               ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return locationDAO.delete(id);
    }

    @Override
    public List<LocationDTO> getAll() throws SQLException, ClassNotFoundException {
List <Location> locations = locationDAO.getAll();
List<LocationDTO> locationDTOS = new ArrayList<>();
for (Location l : locations) {
    locationDTOS.add(new LocationDTO(
            l.getCustomerId(),
            l.getId(),
            l.getProvince(),
            l.getCity(),
            l.getAddress(),
            l.getZipCode()));
}
return locationDTOS;
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