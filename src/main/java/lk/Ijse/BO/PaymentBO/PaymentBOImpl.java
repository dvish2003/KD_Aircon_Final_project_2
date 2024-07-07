
package lk.Ijse.BO.PaymentBO;

import lk.Ijse.DAO.PaymentDAO.PaymentDAO;
import lk.Ijse.DAO.PaymentDAO.PaymentDAOImpl;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(
                dto.getPaymentId(),
                dto.getPaymentAmount(),
                dto.getPaymentDate()));
    }

    @Override
    public Payment searchById(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.searchById(id);
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
List<Payment> payment = paymentDAO.getAll();
    return payment;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
List<String> payments = paymentDAO.getIds();
List<String> ids = new ArrayList<>();
    for (String id : payments) {
        ids.add(id);
    }
    return ids;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentId();
    }
}




