package lk.Ijse.DAO.LocationDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO {

    public  boolean save(Location location) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO Location (Customer_id, Location_id, Location_Province, Location_City, Location_Address, Location_ZipCode) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, location.getCustomerId());
        pstm.setString(2, location.getId());
        pstm.setString(3, location.getProvince());
        pstm.setString(4, location.getCity());
        pstm.setString(5, location.getAddress());
        pstm.setString(6, location.getZipCode());

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("INSERT INTO Location (Customer_id, Location_id, Location_Province, Location_City, Location_Address, Location_ZipCode) VALUES (?, ?, ?, ?, ?, ?)",
                location.getCustomerId(),
                location.getId(),
                location.getProvince(),
                location.getCity(),
                location.getAddress(),
                location.getZipCode());

    }

    public   Location searchById(String id) throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT * FROM Location WHERE Location_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);*/
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Location WHERE Location_id = ?",id);

        if (resultSet.next()) {
            String customerId = resultSet.getString("Customer_id");
            String loId = resultSet.getString("Location_id");
            String loProvince = resultSet.getString("Location_Province");
            String loCity = resultSet.getString("Location_City");
            String loAddress = resultSet.getString("Location_Address");
            String loZipCode = resultSet.getString("Location_ZipCode");
            return new   Location(customerId,loId, loProvince, loCity, loAddress, loZipCode);
        }
        return null;
    }

    public  boolean update(Location location) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE Location SET Customer_id = ? ,Location_Province = ?, Location_City = ?, Location_Address = ?, Location_ZipCode = ? WHERE Location_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, location.getCustomerId());
        pstm.setString(2, location.getProvince());
        pstm.setString(3, location.getCity());
        pstm.setString(4, location.getAddress());
        pstm.setString(5, location.getZipCode());
        pstm.setString(6, location.getId());

        return pstm.executeUpdate() > 0;*/
            return SqlUtil.execute("UPDATE Location SET Customer_id = ? ,Location_Province = ?, Location_City = ?, Location_Address = ?, Location_ZipCode = ? WHERE Location_id = ?",
                location.getCustomerId(),
                location.getProvince(),
                location.getCity(),
                location.getAddress(),
                location.getZipCode(),
                location.getId());
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "DELETE FROM Location WHERE Location_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("DELETE FROM Location WHERE Location_id = ?",id);

    }

    public  List<Location> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM Location";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Location");
        List<Location> locationList = new ArrayList<>();
        while (resultSet.next()) {

            String customerId = resultSet.getString("Customer_id");
            String loId = resultSet.getString("Location_id");
            String loProvince = resultSet.getString("Location_Province");
            String loCity = resultSet.getString("Location_City");
            String loAddress = resultSet.getString("Location_Address");
            String loZipCode = resultSet.getString("Location_ZipCode");


            locationList.add(new Location(customerId,loId,loProvince,loCity,loAddress,loZipCode));
        }
        return locationList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
     /*   String sql = "SELECT Location_id FROM Location";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.execute("SELECT Location_id FROM Location");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT Location_id FROM Location ORDER BY Location_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);*/

        ResultSet resultSet = SqlUtil.execute("SELECT Location_id FROM Location ORDER BY Location_id DESC LIMIT 1");
        if(resultSet.next()) {
            String Location_id = resultSet.getString(1);
            return Location_id;
        }
        return null;
    }
}