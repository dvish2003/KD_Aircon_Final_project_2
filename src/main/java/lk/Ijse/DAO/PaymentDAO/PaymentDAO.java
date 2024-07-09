package lk.Ijse.DAO.PaymentDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Payment;
import lk.Ijse.dto.PaymentDTO;

import java.sql.*;

public interface PaymentDAO extends CrudDAO<Payment> {

    public Payment searchById(String id) throws SQLException, ClassNotFoundException;
    public String generateNextPay(String currentId) ;


    }
