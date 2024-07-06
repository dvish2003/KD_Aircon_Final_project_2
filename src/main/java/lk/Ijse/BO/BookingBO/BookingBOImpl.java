package lk.Ijse.BO.BookingBO;

import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.Entity.Booking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBOImpl implements BookingBO {

BookingDAO bookingDAO = new BookingDAOImpl();
    @Override
    public boolean save(Booking booking) throws SQLException, ClassNotFoundException {
        return bookingDAO.save(new Booking(
                booking.getBookingId(),
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription()));
    }

    @Override
    public Booking searchById(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.searchById(id);
    }

    @Override
    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        return bookingDAO.update(new Booking(
                booking.getBookingId(),
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription()));    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.delete(id);
    }

    @Override
    public List<Booking> getAll() throws SQLException, ClassNotFoundException {
       List<Booking> booking = bookingDAO.getAll();
        return booking;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> Booking = new ArrayList<>();
        List<String> ids = bookingDAO.getIds();
        for(String getIds : ids) {
            Booking.add(getIds);
        }
        return Booking;
        }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return bookingDAO.getCurrentId();
    }
}



