
package lk.Ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.Ijse.Model.Customer;
import lk.Ijse.Model.Products;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.ProductsRepo;

import java.sql.SQLException;
import java.util.List;

public class ProductController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPrBack;

    @FXML
    private Button btnPrClean;

    @FXML
    private Button btnPrDelete;

    @FXML
    private Button btnPrNext;

    @FXML
    private Button btnPrSave;

    @FXML
    private Button btnPrUpdate;
    @FXML
    private TableColumn<?, ?> colPrDesc;

    @FXML
    private TableColumn<?, ?> colPrID;

    @FXML
    private TableView<Products> colPrTel;

    @FXML
    private TableColumn<?, ?> colPrUnitPrice;


    @FXML
    private TextField txtPrDesc;

    @FXML
    private TextField txtPrID;

    @FXML
    private TextField txtPrUnitPrice;

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrCleanOnAction(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnPrDeleteOnAction(ActionEvent event) {
        String id = txtPrID.getText();
        try {
            boolean isDeleted = ProductsRepo.delete(id);
            if (isDeleted) {
                Products selectedCustomer = colPrTel.getSelectionModel().getSelectedItem();
                if (selectedCustomer != null) {
                    colPrTel.getItems().remove(selectedCustomer);
                    new Alert(Alert.AlertType.CONFIRMATION, "Product deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting Product: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrNextOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrSaveOnAction(ActionEvent event) {
   String P_ID = txtPrID.getText();
   String P_DESC = txtPrDesc.getText();
   int P_UNIT_PRICE = Integer.parseInt(txtPrUnitPrice.getText());

        Products products = new Products(P_ID,P_DESC,P_UNIT_PRICE);
        try {
            boolean isSaved = ProductsRepo.save(products);
            if (isSaved) {
                // Add the new customer to the TableView
                colPrTel.getItems().add(products);
                new Alert(Alert.AlertType.CONFIRMATION, "products saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save products!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving products: " + e.getMessage()).show();
        }


    }

    @FXML
    void btnPrUpdateOnAction(ActionEvent event) {
        String P_ID = txtPrID.getText();
        String P_DESC = txtPrDesc.getText();
        int P_UNIT_PRICE = Integer.parseInt(txtPrUnitPrice.getText());

        Products products = new Products(P_ID,P_DESC,P_UNIT_PRICE);
        try {
            boolean isUpdated = ProductsRepo.update(products);
            if (isUpdated) {
                Products selectedItem = colPrTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colPrTel.getItems().indexOf(selectedItem);
                    colPrTel.getItems().set(selectedIndex, products);
                    new Alert(Alert.AlertType.CONFIRMATION, "products updated successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update products!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating products: " + e.getMessage()).show();
        }

    }
    public void initialize() {
        setCellValueFactory();
        loadAllProducts();

        txtPrID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrDesc.requestFocus();
            }
        });

        txtPrDesc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrUnitPrice.requestFocus();
            }
        });



        colPrTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                txtPrID.setText(newSelection.getProductId());
                txtPrDesc.setText(newSelection.getProductDescription());
                txtPrUnitPrice.setText(String.valueOf(newSelection.getProductUnitPrice()));

            }
        });
    }

    private void setCellValueFactory() {
        colPrID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colPrDesc.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        colPrUnitPrice.setCellValueFactory(new PropertyValueFactory<>("productUnitPrice"));
    

    }

    private void loadAllProducts() {
        ObservableList<Products> obList = FXCollections.observableArrayList();

        try {
            List<Products> productsList = ProductsRepo.getAll();
            for (Products products : productsList) {
                Products tm = new Products(
                        products.getProductId(),
                        products.getProductDescription(),
                        products.getProductUnitPrice()

                );

                obList.add(tm);
            }

            colPrTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void clearFields() {
        txtPrID.clear();
        txtPrDesc.clear();
        txtPrUnitPrice.clear();

    }
}
