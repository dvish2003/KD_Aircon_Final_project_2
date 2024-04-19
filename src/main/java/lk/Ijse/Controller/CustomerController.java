package lk.Ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lk.Ijse.Model.Customer;
import lk.Ijse.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClean;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<Customer> colCuTel;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtCuAddress;

    @FXML
    private TextField txtCuContact;

    @FXML
    private TextField txtCuEmail;

    @FXML
    private TextField txtCuId;

    @FXML
    private TextField txtCuName;

    public void initialize() {
            setCellValueFactory();
            loadAllCustomers();

        txtCuId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCuName.requestFocus();
            }
        });

        txtCuName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCuAddress.requestFocus();
            }
        });

        txtCuAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCuContact.requestFocus();
            }
        });

        txtCuContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCuEmail.requestFocus();
            }
        });

            colCuTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {

                    txtCuId.setText(newSelection.getId());
                    txtCuName.setText(newSelection.getName());
                    txtCuAddress.setText(newSelection.getAddress());
                    txtCuContact.setText(newSelection.getContact());
                    txtCuEmail.setText(newSelection.getEmail());
                }
            });
        }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    private void loadAllCustomers() {
        ObservableList<Customer> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                Customer tm = new Customer(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getContact(),
                        customer.getEmail()
                );

                obList.add(tm);
            }

            colCuTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/DashBoard_from.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnCleanOnAction(ActionEvent event) {

        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

            String id = txtCuId.getText();
            try {
                boolean isDeleted = CustomerRepo.delete(id);
                if (isDeleted) {
                    // Remove the selected item from the TableView
                    Customer selectedCustomer = colCuTel.getSelectionModel().getSelectedItem();
                    if (selectedCustomer != null) {
                        colCuTel.getItems().remove(selectedCustomer);
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully!").show();
                        clearFields();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting customer: " + e.getMessage()).show();
            }
        }


    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String id = txtCuId.getText();
        String name = txtCuName.getText();
        String address = txtCuAddress.getText();
        String contact = txtCuContact.getText();
        String email = txtCuEmail.getText();

        Customer customer = new Customer(id, name, address, contact, email);

        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {

                colCuTel.getItems().add(customer);
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving customer: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCuId.getText();
        String name = txtCuName.getText();
        String address = txtCuAddress.getText();
        String contact = txtCuContact.getText();
        String email = txtCuEmail.getText();

        Customer customer = new Customer(id, name, address, contact, email);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                Customer selectedItem = colCuTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colCuTel.getItems().indexOf(selectedItem);
                    colCuTel.getItems().set(selectedIndex, customer);
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating customer: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        // Method to clear input fields
        txtCuId.clear();
        txtCuName.clear();
        txtCuAddress.clear();
        txtCuContact.clear();
        txtCuEmail.clear();
    }

    public void btnNextOnAction(ActionEvent event) {
    }

    public void btnHomeOnAction(ActionEvent event) {
    }
}
