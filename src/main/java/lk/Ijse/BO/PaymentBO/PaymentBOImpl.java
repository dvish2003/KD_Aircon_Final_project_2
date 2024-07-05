
package lk.Ijse.BO.PaymentBO;

import lk.Ijse.DAO.SqlUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    public  boolean save(lk.Ijse.Model.Payment dto) throws SQLException, ClassNotFoundException {
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
    public boolean update(lk.Ijse.Model.Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public lk.Ijse.Model.Payment searchById(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Payment WHERE Payment_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);*/
        ResultSet rs = SqlUtil.execute( "SELECT * FROM Payment WHERE Payment_id = ?",id);
        if (rs.next()){
            String Pay_ID = rs.getString(1);
            int Amount = Integer.parseInt(rs.getString(2));
            Date date = Date.valueOf(rs.getString(3));


        lk.Ijse.Model.Payment payment = new lk.Ijse.Model.Payment(Pay_ID,Amount,date);
            return payment;

        }
        return null;

    }

    public  List<lk.Ijse.Model.Payment> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Payment");

        List<lk.Ijse.Model.Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String Pay_ID = resultSet.getString(1);
            int Amount = Integer.parseInt(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));

            lk.Ijse.Model.Payment payment = new lk.Ijse.Model.Payment(Pay_ID,Amount,date);
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




