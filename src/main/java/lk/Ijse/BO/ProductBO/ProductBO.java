package lk.Ijse.BO.ProductBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Products;
import lk.Ijse.dto.OrderDetailDTO;
import lk.Ijse.dto.ProductsDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {
    public  boolean save(ProductsDTO product) throws SQLException, ClassNotFoundException ;

    public Products searchById(String id) throws SQLException, ClassNotFoundException ;

    public Products searchByName(String Description) throws SQLException, ClassNotFoundException ;

    public  boolean update(ProductsDTO product) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  List<ProductsDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  List<String> getNames() throws SQLException, ClassNotFoundException  ;

    public  boolean update1(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException ;

    public  boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException ;

    public  String  getCurrentId() throws SQLException, ClassNotFoundException ;

}