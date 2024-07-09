package lk.Ijse.BO.PaymentBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public  boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException ;

    public PaymentDTO searchById(String id) throws SQLException, ClassNotFoundException;


    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException;


    public String getCurrentId() throws SQLException, ClassNotFoundException;
    public String generateNextPay(String currentId) ;



}
