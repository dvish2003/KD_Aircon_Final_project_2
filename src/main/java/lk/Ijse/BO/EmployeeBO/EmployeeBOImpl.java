package lk.Ijse.BO.EmployeeBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAO;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;
import lk.Ijse.Entity.Employee;
import lk.Ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
 EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Employee);
    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                employeeDTO.getEmpId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmpAge(),
                employeeDTO.getEmpAddress(),
                employeeDTO.getEmpPhone(),
                employeeDTO.getEmpEmail()
        ));
    }

    @Override
    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchById(id);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                        employeeDTO.getEmpId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmpAge(),
                employeeDTO.getEmpAddress(),
                employeeDTO.getEmpPhone(),
                employeeDTO.getEmpEmail()
)
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
List<Employee> employees = employeeDAO.getAll();
    List<EmployeeDTO> employeeDTOS = new ArrayList<>();
    for (Employee e : employees) {

    employeeDTOS.add(new EmployeeDTO(e.getEmpId(),e.getEmpName(),e.getEmpAge(),
            e.getEmpAddress(),e.getEmpPhone(),e.getEmpEmail()));
    }
    return employeeDTOS;
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
