package lk.Ijse.DAO.RegisterDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Register;

import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO <Register> {
    public  Register searchById(String id) throws SQLException, ClassNotFoundException;
}
