
package lk.Ijse.BO.PaymentBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.PaymentDAO.PaymentDAO;
import lk.Ijse.DAO.PaymentDAO.PaymentDAOImpl;
import lk.Ijse.Entity.Payment;
import lk.Ijse.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Payment);

    @Override
    public boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(
                dto.getPaymentId(),
                dto.getPaymentAmount(),
                dto.getPaymentDate()));
    }

    @Override
    public PaymentDTO searchById(String id) throws SQLException, ClassNotFoundException {
return null;
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
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

    @Override
    public String generateNextPay(String currentId) {
return paymentDAO.generateNextPay(currentId);    }
}




