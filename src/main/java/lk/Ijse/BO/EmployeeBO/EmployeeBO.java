package lk.Ijse.BO.EmployeeBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Employee;
import lk.Ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
    public Employee searchById(String id) throws SQLException, ClassNotFoundException ;
    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;
    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  String getCurrentId() throws SQLException, ClassNotFoundException ;
}
