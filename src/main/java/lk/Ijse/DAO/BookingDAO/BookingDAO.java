package lk.Ijse.DAO.BookingDAO;

import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Entity.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface BookingDAO extends CrudDAO<Booking> {

    public  Booking searchById(String id) throws SQLException, ClassNotFoundException;




}
