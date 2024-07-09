package lk.Ijse.DAO.CustomerDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Customer;
import lk.Ijse.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException {
           return SqlUtil.execute("INSERT INTO Customer VALUES(?,?,?,?,?)", customer.getId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail());
    }

    @Override
    public Customer searchById(String id1) throws SQLException, ClassNotFoundException {

 ResultSet rs = SqlUtil.execute("SELECT * FROM Customer WHERE Customer_Contact = ?",id1);
 if (rs.next()){
     String cus_id = rs.getString(1);
     String Name = rs.getString(2);
     String Address = rs.getString(3);
     String contact = rs.getString(4);
     String Email = rs.getString(5);

     Customer customer = new Customer(cus_id,Name,Address,contact,Email);
     System.out.println("Customer");
      return customer;

 }
        System.out.println("Null");
 return null;

    }
    @Override
    public Customer searchById1(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SqlUtil.execute("SELECT * FROM Customer WHERE Customer_id = ?",id);
        if (rs.next()) {
            String cus_id = rs.getString(1);
            String Name = rs.getString(2);
            String Address = rs.getString(3);
            String contact = rs.getString(4);
            String Email = rs.getString(5);

            Customer customer = new Customer(cus_id, Name, Address, contact, Email);

            return customer;

        }

        return null;
    }

    @Override
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Customer SET Customer_Name = ?, Customer_Address = ?, Customer_Contact = ?,Customer_Email = ? WHERE Customer_id = ?",

                customer.getName(),
                customer.getAddress(),
                customer.getContact(),
                customer.getEmail(),
                customer.getId());




    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Customer WHERE Customer_id = ?",id);


    }

    @Override
    public  List<Customer> getAll() throws SQLException, ClassNotFoundException {
         List<Customer> cusList = new ArrayList<>();
         ResultSet resultSet = SqlUtil.execute("SELECT * FROM Customer");

        while (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String Name = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Contact = resultSet.getString(4);
            String Email = resultSet.getString(5);
            Customer customer = new Customer(cus_id,Name,Address,Contact,Email);
            cusList.add(customer);
        }
        return cusList;
    }


    @Override
    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.execute("SELECT Customer_id FROM Customer");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public  String getCurrentId() throws SQLException, ClassNotFoundException {
      ResultSet resultSet = SqlUtil.execute("SELECT Customer_id FROM Customer ORDER BY Customer_id DESC LIMIT 1");
        if(resultSet.next()) {
            String Customer_id = resultSet.getString(1);
            return Customer_id;
        }
        return null;
    }
}




