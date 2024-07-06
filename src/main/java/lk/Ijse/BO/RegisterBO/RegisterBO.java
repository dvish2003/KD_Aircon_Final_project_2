package lk.Ijse.BO.RegisterBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Register;

import java.sql.SQLException;
import java.util.List;

public interface RegisterBO extends SuperBO {
    public boolean save(Register register) throws SQLException, ClassNotFoundException;


    public Register searchById(String id) throws SQLException, ClassNotFoundException;

    public boolean update(Register register) throws SQLException, ClassNotFoundException;


    public boolean delete(String id) throws SQLException, ClassNotFoundException;


    public List<Register> getAll() throws SQLException, ClassNotFoundException;


    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;
}
