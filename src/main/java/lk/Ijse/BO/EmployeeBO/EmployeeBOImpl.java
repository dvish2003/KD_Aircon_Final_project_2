package lk.Ijse.BO.EmployeeBO;

import lk.Ijse.DAO.EmployeeDAO.EmployeeDAO;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
 EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getEmpAge(),
                employee.getEmpAddress(),
                employee.getEmpPhone(),
                employee.getEmpEmail()
        ));
    }

    @Override
    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchById(id);
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                employee.getEmpName(),
                employee.getEmpAge(),
                employee.getEmpAddress(),
                employee.getEmpPhone(),
                employee.getEmpEmail(),
                employee.getEmpId())
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
    List<Employee> employeeList =employeeDAO.getAll();
    return employeeList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
    List<String> employeeList =employeeDAO.getIds();
    List<String> employeeIdList =new ArrayList<>();
    for (String id : employeeList){
    employeeIdList.add(id);
}
return employeeIdList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentId();
    }
}
