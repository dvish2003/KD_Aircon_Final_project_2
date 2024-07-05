package lk.Ijse.BO.LocationBO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Model.Location;

import java.sql.SQLException;

public interface LocationBO extends CrudDAO <Location> {
    public   Location searchById(String id) throws SQLException, ClassNotFoundException;

    }
