package lk.Ijse.BO.ProductBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.Entity.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {
    public  boolean save(Products product) throws SQLException, ClassNotFoundException ;

    public  Products searchById(String id) throws SQLException, ClassNotFoundException ;

    public  Products searchByName(String Description) throws SQLException, ClassNotFoundException ;

    public  boolean update(Products product) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  List<Products> getAll() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  List<String> getNames() throws SQLException, ClassNotFoundException  ;

    public  boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException ;

    public  boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException ;

    public  String  getCurrentId() throws SQLException, ClassNotFoundException ;

}