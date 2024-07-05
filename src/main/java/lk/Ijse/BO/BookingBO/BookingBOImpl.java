package lk.Ijse.BO.BookingBO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Model.Booking;
import lk.Ijse.entity.BookingEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBOImpl implements BookingBO {


    @Override
    public boolean save(Booking booking) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Booking searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<BookingEntity> getAll() throws SQLException, ClassNotFoundException {
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



