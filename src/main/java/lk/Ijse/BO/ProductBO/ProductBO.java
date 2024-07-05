package lk.Ijse.BO.ProductBO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Model.OrderDetail;
import lk.Ijse.Model.Products;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends CrudDAO<Products> {
    public  Products searchById(String id) throws SQLException, ClassNotFoundException;
    public  Products searchByName(String Description) throws SQLException, ClassNotFoundException;
    public  List<String> getNames() throws SQLException, ClassNotFoundException;
    public  boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;
    public  boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException;

}
