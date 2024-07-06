package lk.Ijse.BO.PaymentBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public  boolean save(lk.Ijse.Entity.Payment dto) throws SQLException, ClassNotFoundException ;

    public lk.Ijse.Entity.Payment searchById(String id) throws SQLException, ClassNotFoundException;


    public List<Payment> getAll() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException;


    public String getCurrentId() throws SQLException, ClassNotFoundException;



}
