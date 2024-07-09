package lk.Ijse.DAO.BookingDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Entity.Booking;
import lk.Ijse.dto.BookingDTO;

import java.sql.*;

public interface BookingDAO extends CrudDAO<Booking> {

    public Booking searchById(String id) throws SQLException, ClassNotFoundException;




}
