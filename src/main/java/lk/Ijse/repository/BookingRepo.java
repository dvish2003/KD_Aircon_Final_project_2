package lk.Ijse.repository;/*package lk.Ijse.repository;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingRepo {
     public static boolean addBooking(Booking booking) throws SQLException {
            String sql = "INSERT INTO booking  VALUES (?,?,?,?,?,?,?)";

          Connection connection = DbConnection.getInstance().getConnection();
          PreparedStatement pstm = connection.prepareStatement(sql);
          pstm.setString(1, booking.getBookingId());
          pstm.setString(2, booking.getBookingTime());


         return false;
     }
}*/