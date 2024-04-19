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
import lk.Ijse.Model.ShowRoom;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.ProductsRepo;
import lk.Ijse.repository.ShowRoomRepo;

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
    private ComboBox<String> cmbShowRoom; // Corrected ComboBox type

    @FXML
    private TableColumn<Products, String> colPrDesc; // Corrected TableColumn type

    @FXML
    private TableColumn<Products, String> colPrID; // Corrected TableColumn type

    @FXML
    private TableView<Products> colPrTel; // Corrected TableView type

    @FXML
    private TableColumn<Products, Integer> colPrUnitPrice; // Corrected TableColumn type

    @FXML
    private TableColumn<Products, Integer> colQtyOnHand; // Assuming this column is for quantity on hand

    @FXML
    private TableColumn<Products, String> colShowRoom; // Assuming this column is for showroom

    @FXML
    private TextField txtPrDesc;

    @FXML
    private TextField txtPrID;

    @FXML
    private TextField txtPrUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        // Handle home button action
    }

    @FXML
    void btnPrBackOnAction(ActionEvent event) {
        // Handle back button action
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
                Products selectedProduct = colPrTel.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    colPrTel.getItems().remove(selectedProduct);
                    new Alert(Alert.AlertType.CONFIRMATION, "Product deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting product: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrNextOnAction(ActionEvent event) {
        // Handle next button action
    }

    @FXML
    void btnPrSaveOnAction(ActionEvent event) {
        String P_ID = txtPrID.getText();
        String P_DESC = txtPrDesc.getText();
        int P_UNIT_PRICE = Integer.parseInt(txtPrUnitPrice.getText());

        Products product = new Products(P_ID, P_DESC, P_UNIT_PRICE);
        try {
            boolean isSaved = ProductsRepo.save(product);
            if (isSaved) {
                colPrTel.getItems().add(product);
                new Alert(Alert.AlertType.CONFIRMATION, "Product saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving product: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrUpdateOnAction(ActionEvent event) {
        String P_ID = txtPrID.getText();
        String P_DESC = txtPrDesc.getText();
        int P_UNIT_PRICE = Integer.parseInt(txtPrUnitPrice.getText());

        Products product = new Products(P_ID, P_DESC, P_UNIT_PRICE);
        try {
            boolean isUpdated = ProductsRepo.update(product);
            if (isUpdated) {
                Products selectedItem = colPrTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colPrTel.getItems().indexOf(selectedItem);
                    colPrTel.getItems().set(selectedIndex, product);
                    new Alert(Alert.AlertType.CONFIRMATION, "Product updated successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating product: " + e.getMessage()).show();
        }
    }

    @FXML
    void cmbShowRoomOnAction(ActionEvent event) {
        String id = cmbShowRoom.getValue();

        try {
            ShowRoom showRoom = ShowRoomRepo.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        loadAllProducts();
        getShowRoomId();

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
            obList.addAll(productsList);
            colPrTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading products: " + e.getMessage(), e);
        }
    }

    private void clearFields() {
        txtPrID.clear();
        txtPrDesc.clear();
        txtPrUnitPrice.clear();
    }
    private void getShowRoomId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = ShowRoomRepo.getIds();
            obList.addAll(idList);
            cmbShowRoom.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
