package lk.Ijse.DAO.BookingDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    public  boolean save(Booking booking) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO Booking (Booking_id, Employee_id, Location_id, Payment_id, Booking_Date, Place_Date, Booking_Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, booking.getBookingId());
        pstm.setString(2, booking.getEmpId());
        pstm.setString(3, booking.getLocId());
        pstm.setString(4, booking.getPaymentId());
        pstm.setDate(5, booking.getBookingDate());
        pstm.setDate(6, booking.getPlaceDate());
        pstm.setString(7, booking.getBookingDescription());

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("INSERT INTO Booking (Booking_id, Employee_id, Location_id, Payment_id, Booking_Date, Place_Date, Booking_Description) VALUES (?, ?, ?, ?, ?, ?, ?)",
                booking.getBookingId(),
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription());

    }

    public  Booking searchById(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Booking WHERE Booking_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);*/
        ResultSet rs = SqlUtil.execute("SELECT * FROM Booking WHERE Booking_id = ?",id);
        if (rs.next()){
            String BookingId = rs.getString(1);
            String empId = rs.getString(2);
            String LocId = rs.getString(3);
            String paymentId = rs.getString(4);
            Date bookingDate = rs.getDate(5);
            Date PlaceDate = rs.getDate(6);
            String bookingDescription = rs.getString(7);
            Booking booking = new Booking(BookingId,empId,LocId,paymentId,bookingDate,PlaceDate,bookingDescription);
return booking;
        }
        return null;

    }

    public  boolean update(Booking booking) throws SQLException, ClassNotFoundException {
      /*  String sql = "UPDATE Booking SET Employee_id = ?, Location_id = ?, Payment_id = ?, Booking_Date = ?, Place_Date = ?, Booking_Description = ? WHERE Booking_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, booking.getEmpId());
        pstm.setString(2, booking.getLocId());
        pstm.setString(3, booking.getPaymentId());
        pstm.setDate(4,   booking.getBookingDate());
        pstm.setDate(5,   booking.getPlaceDate());
        pstm.setString(6, booking.getBookingDescription());
        pstm.setString(7, booking.getBookingId());
        return pstm.executeUpdate() > 0;*/

        return SqlUtil.execute( "UPDATE Booking SET Employee_id = ?, Location_id = ?, Payment_id = ?, Booking_Date = ?, Place_Date = ?, Booking_Description = ? WHERE Booking_id = ?",
                booking.getEmpId(),
                booking.getLocId(),
                booking.getPaymentId(),
                booking.getBookingDate(),
                booking.getPlaceDate(),
                booking.getBookingDescription(),
                booking.getBookingId());

    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
      /*  String sql = "DELETE FROM Booking WHERE Booking_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);*/

        return SqlUtil.execute("DELETE FROM Booking WHERE Booking_id = ?",id);
    }
    public  List<Booking> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Booking";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

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
            Booking booking = new Booking(BookingId,empId,LocId,paymentId,bookingDate,PlaceDate,bookingDescription);
            bookList.add(booking);
        }
        return bookList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT Booking_id FROM Booking";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
*/
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
        return "";
    }

    /*  public  String getCurrentId() throws SQLException, ClassNotFoundException {
        *//*String sql = "SELECT Booking_id FROM Booking ORDER BY Booking_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*//*

        ResultSet resultSet =SqlUtil.execute("SELECT Booking_id FROM Booking ORDER BY Booking_id DESC LIMIT 1");
        if(resultSet.next()) {
            String BookingID = resultSet.getString(1);
            return BookingID;
        }
        return null;
    }*/



}



