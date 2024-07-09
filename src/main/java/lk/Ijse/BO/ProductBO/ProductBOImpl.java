package lk.Ijse.BO.ProductBO;

import lk.Ijse.DAO.DAOFactory;
import lk.Ijse.DAO.ProductDAO.ProductDAO;
import lk.Ijse.DAO.ProductDAO.ProductDAOImpl;
import lk.Ijse.Entity.Products;
import lk.Ijse.dto.OrderDetailDTO;
import lk.Ijse.dto.ProductsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.Products);
    @Override
    public boolean save(ProductsDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Products( product.getProduct_id(),
                product.getProduct_description(),
                product.getShowRoom_qtyOnHand(),
                product.getProduct_unitPrice()));
    }

    @Override
    public Products searchById(String id) throws SQLException, ClassNotFoundException {
        return productDAO.searchById(id);     }

    @Override
    public Products searchByName(String Description) throws SQLException, ClassNotFoundException {
return productDAO.searchByName(Description);
    }

    @Override
    public boolean update(ProductsDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Products(
                product.getProduct_id(),
                product.getProduct_description(),
                product.getShowRoom_qtyOnHand(),
                product.getProduct_unitPrice()
                ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public List<ProductsDTO> getAll() throws SQLException, ClassNotFoundException {
List<Products> products = productDAO.getAll();
    List<ProductsDTO> productDTOS = new ArrayList<>();
    for (Products p : products) {
        productDTOS.add(new ProductsDTO(
                p.getProduct_id(),
                p.getProduct_description(),
                p.getShowRoom_qtyOnHand(),
                p.getProduct_unitPrice()));
    }
    return productDTOS;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
   List<String> Ids = productDAO.getIds();
        List<String> ids = new ArrayList<>();
    for (String id : Ids) {
    ids.add(id);
    }
    return Ids;
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        List<String> Name = productDAO.getNames();
        List<String> names = new ArrayList<>();
        for (String name : Name) {
            names.add(name);
        }
        return names;
        }

    @Override
    public boolean update1(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {
            boolean isUpdateQty = updateQty(od.getProductId(), od.getQuantity());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(String Id, int qty) throws SQLException, ClassNotFoundException {
        return productDAO.updateQty(Id,qty);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return productDAO.getCurrentId();
    }
}
