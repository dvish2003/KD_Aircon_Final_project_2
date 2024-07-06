package lk.Ijse.DAO.PaymentDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    public  Payment searchById(String id) throws SQLException, ClassNotFoundException;

}
