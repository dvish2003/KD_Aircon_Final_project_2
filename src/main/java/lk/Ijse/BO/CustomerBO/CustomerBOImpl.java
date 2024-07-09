package lk.Ijse.BO.CustomerBO;

import lk.Ijse.DAO.CustomerDAO.CustomerDAO;
import lk.Ijse.DAO.CustomerDAO.CustomerDAOImpl;
import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.Entity.Customer;
import lk.Ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Customer);
    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail()));
    }

    @Override
    public Customer searchById1(String Contact) throws SQLException, ClassNotFoundException {
    return customerDAO.searchById1(Contact);
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer
                (customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
      List<Customer> customerList = customerDAO.getAll();
      List<CustomerDTO> customerDTOList = new ArrayList<>();
      for (Customer customer : customerList) {
          customerDTOList.add(new CustomerDTO(customer.getId(),
                  customer.getName(),
                  customer.getAddress(),
                  customer.getContact(),
                  customer.getEmail()));
      }
      return customerDTOList;
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
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
    return customerDAO.searchById(id);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCurrentId();
    }
}




