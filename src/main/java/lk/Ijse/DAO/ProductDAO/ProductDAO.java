package lk.Ijse.DAO.ProductDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.Entity.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends CrudDAO<Products> {
    public  Products searchById(String id) throws SQLException, ClassNotFoundException;
    public  Products searchByName(String Description) throws SQLException, ClassNotFoundException;
    public  List<String> getNames() throws SQLException, ClassNotFoundException;
    public  boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;
    public  boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException;

}
