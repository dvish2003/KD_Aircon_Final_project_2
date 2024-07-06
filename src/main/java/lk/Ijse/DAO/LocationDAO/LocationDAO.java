package lk.Ijse.DAO.LocationDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Location;

import java.sql.SQLException;

public interface LocationDAO extends CrudDAO <Location> {
    public   Location searchById(String id) throws SQLException, ClassNotFoundException;

    }
