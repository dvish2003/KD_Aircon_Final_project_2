package lk.Ijse.BO.BookingBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.Entity.Booking;
import lk.Ijse.dto.BookingDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookingBO extends SuperBO {

    public  boolean save(BookingDTO bookingDTO) throws SQLException, ClassNotFoundException;

    public Booking searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(BookingDTO bookingDTO) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<BookingDTO> getAll() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;



}
