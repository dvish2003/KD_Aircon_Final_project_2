package lk.Ijse.repository;

import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Product_ShowRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product_ShowRoom_Repo {

    public static boolean save(Product_ShowRoom ps) throws SQLException {
        String sql = "INSERT INTO Product_ShowRoom  VALUES(?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, ps.getProductID());
            pstm.setString(2, ps.getShowRoomId());

            return pstm.executeUpdate() > 0;

        }
    }

