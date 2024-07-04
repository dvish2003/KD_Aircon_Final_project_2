package lk.Ijse.BO.CustomerBO;

import lk.Ijse.Model.Customer;
import lk.Ijse.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {

    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException ;

    public  Customer searchById1(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<CustomerEntity> getAll() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  Customer searchById(String Contact) throws SQLException, ClassNotFoundException;

 // public  Customer searchById1(String id) throws SQLException, ClassNotFoundException;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

}
