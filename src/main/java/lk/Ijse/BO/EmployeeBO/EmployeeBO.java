package lk.Ijse.BO.EmployeeBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public  boolean save(Employee employee) throws SQLException, ClassNotFoundException ;
    public  Employee searchById(String id) throws SQLException, ClassNotFoundException ;
    public  boolean update(Employee employee) throws SQLException, ClassNotFoundException ;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public List<Employee> getAll() throws SQLException, ClassNotFoundException;
    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  String getCurrentId() throws SQLException, ClassNotFoundException ;
}
