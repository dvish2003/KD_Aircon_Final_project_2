package lk.Ijse.BO.CustomerBO;

import lk.Ijse.DAO.CustomerDAO.CustomerDAO;
import lk.Ijse.DAO.CustomerDAO.CustomerDAOImpl;
import lk.Ijse.Entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail()));
    }

    @Override
    public Customer searchById1(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.searchById(id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
      List<Customer> customerList = customerDAO.getAll();
      return customerList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
         List<String> CustomerIds = new ArrayList<>();
         List<String> ids = customerDAO.getIds();
         for(String getIds : ids){
         CustomerIds.add(getIds);

}

        return CustomerIds;
    }

    @Override
    public Customer searchById(String Contact) throws SQLException, ClassNotFoundException {
        return customerDAO.searchById(Contact);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCurrentId();
    }
}




