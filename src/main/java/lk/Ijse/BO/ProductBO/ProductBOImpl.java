package lk.Ijse.BO.ProductBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.OrderDetail;
import lk.Ijse.Entity.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {


    @Override
    public boolean save(Products product) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Products searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Products searchByName(String Description) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Products product) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Products> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }
}
