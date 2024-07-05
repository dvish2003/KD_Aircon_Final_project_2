package lk.Ijse.BO.RegisterBO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Model.Register;

import java.sql.SQLException;

public interface RegisterBO extends CrudDAO <Register> {
    public  Register searchById(String id) throws SQLException, ClassNotFoundException;
}
