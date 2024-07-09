package lk.Ijse.BO.RegisterBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Register;
import lk.Ijse.dto.RegisterDTO;

import java.sql.SQLException;
import java.util.List;

public interface RegisterBO extends SuperBO {
    public boolean save(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException;


    public Register searchById(String id) throws SQLException, ClassNotFoundException;

    public boolean update(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException;


    public boolean delete(String id) throws SQLException, ClassNotFoundException;


    public List<RegisterDTO> getAll() throws SQLException, ClassNotFoundException;


    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;
}
