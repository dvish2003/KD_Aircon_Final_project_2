package lk.Ijse.DAO.ProductShowroomDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.ProductShowRoomJoin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductShowRoomJoinDAOImpl implements ProductShowRoomJoinDAO {
    @Override
    public boolean save(ProductShowRoomJoin dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ProductShowRoomJoin dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  List<ProductShowRoomJoin> getAll() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT \n" +
                "    Product.Product_id,\n" +
                "    Product.Product_Description,\n" +
                "    Product.ShowRoom_QtyOnHand,\n" +
                "    Product.Product_UnitPrice,\n" +
                "    Product_ShowRoom.ShowRoom_id\n" +
                "FROM \n" +
                "    Product\n" +
                "JOIN \n" +
                "    Product_ShowRoom ON Product.Product_id = Product_ShowRoom.Product_id;\n";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        ResultSet resultSet = SqlUtil.execute("SELECT \n" +
                "    Product.Product_id,\n" +
                "    Product.Product_Description,\n" +
                "    Product.ShowRoom_QtyOnHand,\n" +
                "    Product.Product_UnitPrice,\n" +
                "    Product_ShowRoom.ShowRoom_id\n" +
                "FROM \n" +
                "    Product\n" +
                "JOIN \n" +
                "    Product_ShowRoom ON Product.Product_id = Product_ShowRoom.Product_id;\n");
        List<ProductShowRoomJoin> productShowRoomJoinList = new ArrayList<>();
        while (resultSet.next()) {
            String showRoomId = resultSet.getString("ShowRoom_id");
            String productId = resultSet.getString("Product_id");
            String productDescription = resultSet.getString("Product_Description");
            int showRoomQtyOnHand = resultSet.getInt("ShowRoom_QtyOnHand");
            int productUnitPrice = resultSet.getInt("Product_UnitPrice");


            productShowRoomJoinList.add(new ProductShowRoomJoin(showRoomId,productId, productDescription, showRoomQtyOnHand, productUnitPrice));
        }
        return productShowRoomJoinList;
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
