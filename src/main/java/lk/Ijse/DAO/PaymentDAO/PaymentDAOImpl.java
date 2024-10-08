
package lk.Ijse.DAO.PaymentDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public  boolean save(Payment paymentDTO) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("INSERT INTO Payment VALUES(?,?,?)",
               paymentDTO.getPaymentId(),
               paymentDTO.getPaymentAmount(),
               paymentDTO.getPaymentDate());
    }

    @Override
    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public Payment searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = SqlUtil.execute( "SELECT * FROM Payment WHERE Payment_id = ?",id);
        if (rs.next()){
            String Pay_ID = rs.getString(1);
            int Amount = Integer.parseInt(rs.getString(2));
            Date date = Date.valueOf(rs.getString(3));


        Payment payment = new Payment(Pay_ID,Amount,date);
            return payment;

        }
        return null;

    }

    public  List<lk.Ijse.Entity.Payment> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Payment");

        List<Payment> paymentDTOList = new ArrayList<>();

        while (resultSet.next()) {
            String Pay_ID = resultSet.getString(1);
            int Amount = Integer.parseInt(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));

            Payment paymentDTO = new Payment(Pay_ID,Amount,date);
            paymentDTOList.add(paymentDTO);
        }
        return paymentDTOList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute("SELECT Payment_id FROM Payment");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "SELECT Payment_id FROM Payment ORDER BY Payment_id DESC LIMIT 1");
        if(resultSet.next()) {
            String PayId = resultSet.getString(1);
            return PayId;
        }
        return null;    }
    public String generateNextPay(String currentId) {
        if (currentId != null && currentId.startsWith("P")) {
            try {
                int idNum = Integer.parseInt(currentId.substring(1)) + 1;
                return "P" + String.format("%03d", idNum);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid current payment ID format");
            }
        }
        return "P001";
    }
}




