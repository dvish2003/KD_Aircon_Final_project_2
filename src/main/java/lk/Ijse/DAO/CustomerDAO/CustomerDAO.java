package lk.Ijse.DAO.CustomerDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Customer;
import lk.Ijse.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {

    public Customer searchById(String id) throws SQLException, ClassNotFoundException;


    public Customer searchById1(String Contact) throws SQLException, ClassNotFoundException;




}
