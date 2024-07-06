package lk.Ijse.DAO.EmployeeDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public  Employee searchById(String id) throws SQLException, ClassNotFoundException;
}
