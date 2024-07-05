package lk.Ijse.BO.BookingBO;

import lk.Ijse.BO.SuperBO;
import lk.Ijse.DAO.CrudDAO;
import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Model.Booking;
import lk.Ijse.entity.BookingEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookingBO extends SuperBO {

    public  boolean save(Booking booking) throws SQLException, ClassNotFoundException;

    public  Booking searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(Booking booking) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<BookingEntity> getAll() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;



}
