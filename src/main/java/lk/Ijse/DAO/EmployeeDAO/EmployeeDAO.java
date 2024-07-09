package lk.Ijse.DAO.EmployeeDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Employee;
import lk.Ijse.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public Employee searchById(String id) throws SQLException, ClassNotFoundException;
}
