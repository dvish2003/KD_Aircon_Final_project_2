package lk.Ijse.BO.CustomerBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Customer;
import lk.Ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public  boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public Customer searchById1(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public Customer searchById(String id) throws SQLException, ClassNotFoundException;

 // public  Customer searchById1(String id) throws SQLException, ClassNotFoundException;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

}
