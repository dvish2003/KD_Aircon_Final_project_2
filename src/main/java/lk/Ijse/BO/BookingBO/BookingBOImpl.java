package lk.Ijse.BO.BookingBO;

import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.Entity.Booking;
import lk.Ijse.dto.BookingDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBOImpl implements BookingBO {

BookingDAO bookingDAO = (BookingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Booking);
    @Override
    public boolean save(BookingDTO bookingDTO) throws SQLException, ClassNotFoundException {
        return bookingDAO.save(new Booking(
                bookingDTO.getBookingId(),
                bookingDTO.getEmpId(),
                bookingDTO.getLocId(),
                bookingDTO.getPaymentId(),
                bookingDTO.getBookingDate(),
                bookingDTO.getPlaceDate(),
                bookingDTO.getBookingDescription()));
    }

    @Override
    public Booking searchById(String id) throws SQLException, ClassNotFoundException {
return bookingDAO.searchById(id);
    }

    @Override
    public boolean update(BookingDTO bookingDTO) throws SQLException, ClassNotFoundException {
        return bookingDAO.update(new Booking(
                bookingDTO.getBookingId(),
                bookingDTO.getEmpId(),
                bookingDTO.getLocId(),
                bookingDTO.getPaymentId(),
                bookingDTO.getBookingDate(),
                bookingDTO.getPlaceDate(),
                bookingDTO.getBookingDescription()));    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.delete(id);
    }

    @Override
    public List<BookingDTO> getAll() throws SQLException, ClassNotFoundException {
List<Booking> bookings = bookingDAO.getAll();
List<BookingDTO> bookingDTOS = new ArrayList<>();
for (Booking b : bookings) {
    bookingDTOS.add(new BookingDTO(b.getBookingId(),
            b.getEmpId(),
            b.getLocId(),
            b.getPaymentId(),
            b.getBookingDate(),
            b.getPlaceDate(),
            b.getBookingDescription()));
}
return bookingDTOS;
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



