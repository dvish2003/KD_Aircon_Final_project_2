
package lk.Ijse.BO.PaymentBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }
}




