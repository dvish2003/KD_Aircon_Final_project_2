package lk.Ijse.DAO.BookingDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Booking;
import lk.Ijse.dto.BookingDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    public boolean save(Booking booking) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("INSERT INTO Booking (Booking_id, Employee_id, Location_id, Payment_id, Booking_Date, Place_Date, Booking_Description) VALUES (?, ?, ?, ?, ?, ?, ?)",
                booking.getBookingId(),
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription());

    }

    public Booking searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = SqlUtil.execute("SELECT * FROM Booking WHERE Booking_id = ?", id);
        if (rs.next()) {
            String BookingId = rs.getString(1);
            String empId = rs.getString(2);
            String LocId = rs.getString(3);
            String paymentId = rs.getString(4);
            Date bookingDate = rs.getDate(5);
            Date PlaceDate = rs.getDate(6);
            String bookingDescription = rs.getString(7);
            Booking booking = new Booking(BookingId, empId, LocId, paymentId, bookingDate, PlaceDate, bookingDescription);
            return booking;
        }
        return null;

    }

    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {


        return SqlUtil.execute("UPDATE Booking SET Employee_id = ?, Location_id = ?, Payment_id = ?, Booking_Date = ?, Place_Date = ?, Booking_Description = ? WHERE Booking_id = ?",
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription(),
                booking.getBookingId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("DELETE FROM Booking WHERE Booking_id = ?", id);
    }

    public List<Booking> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Booking");

        List<Booking> bookList = new ArrayList<>();

        while (resultSet.next()) {
            String BookingId = resultSet.getString(1);
            String empId = resultSet.getString(2);
            String LocId = resultSet.getString(3);
            String paymentId = resultSet.getString(4);
            Date bookingDate = resultSet.getDate(5);
            Date PlaceDate = resultSet.getDate(6);
            String bookingDescription = resultSet.getString(7);
            Booking booking = new Booking(BookingId, empId, LocId, paymentId, bookingDate, PlaceDate, bookingDescription);
            bookList.add(booking);
        }
        return bookList;
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute("SELECT Booking_id FROM Booking");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Booking_id FROM Booking ORDER BY Booking_id DESC LIMIT 1");
        if (resultSet.next()) {
            String BookingID = resultSet.getString(1);
            return BookingID;
        }

        return "";
    }
}








