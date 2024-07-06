
package lk.Ijse.DAO.PaymentDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public  boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO Payment VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,dto.getPaymentId());
        pstm.setObject(2,dto.getPaymentAmount());
        pstm.setObject(3, dto.getPaymentDate());
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("INSERT INTO Payment VALUES(?,?,?)",
               dto.getPaymentId(),
               dto.getPaymentAmount(),
               dto.getPaymentDate());
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  Payment searchById(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Payment WHERE Payment_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);*/
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

    public  List<Payment> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Payment");

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String Pay_ID = resultSet.getString(1);
            int Amount = Integer.parseInt(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));

            Payment payment = new Payment(Pay_ID,Amount,date);
            paymentList.add(payment);
        }
        return paymentList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT Payment_id FROM Payment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

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
}




