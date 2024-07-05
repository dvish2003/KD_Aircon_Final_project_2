package lk.Ijse.BO.PaymentBO;

import lk.Ijse.DAO.CrudDAO;

import java.sql.SQLException;

public interface PaymentBO extends CrudDAO<lk.Ijse.Model.Payment> {

    public lk.Ijse.Model.Payment searchById(String id) throws SQLException, ClassNotFoundException;

}
