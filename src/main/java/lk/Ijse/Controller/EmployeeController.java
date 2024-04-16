package lk.Ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.Ijse.Model.Employee;
import lk.Ijse.repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    @FXML
    private Button btnBack1;

    @FXML
    private Button btnEmClean;

    @FXML
    private Button btnEmDelete;

    @FXML
    private Button btnEmSave;

    @FXML
    private Button btnEmUpdate;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnNext1;

    @FXML
    private TableColumn<?, ?> colEmAddress;

    @FXML
    private TableColumn<?, ?> colEmAge;

    @FXML
    private TableColumn<?, ?> colEmContact;

    @FXML
    private TableColumn<?, ?> colEmEmail;

    @FXML
    private TableColumn<?, ?> colEmId;

    @FXML
    private TableColumn<?, ?> colEmName;

    @FXML
    private TableView<Employee> colEmTel;

    @FXML
    private TextField txtEmAddress;

    @FXML
    private TextField txtEmAge;

    @FXML
    private TextField txtEmContact;

    @FXML
    private TextField txtEmEmail;

    @FXML
    private TextField txtEmId;

    @FXML
    private TextField txtEmName;

    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();

        txtEmId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmName.requestFocus();
            }
        });

        txtEmName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmAge.requestFocus();
            }
        });

        txtEmAge.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmAddress.requestFocus();
            }
        });

        txtEmAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmContact.requestFocus();
            }
        });

        txtEmContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmEmail.requestFocus();
            }
        });

        colEmTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtEmId.setText(newSelection.getEmpId());
                txtEmName.setText(newSelection.getEmpName());
                txtEmAge.setText(newSelection.getEmpAge());
                txtEmAddress.setText(newSelection.getEmpAddress());
                txtEmContact.setText(newSelection.getEmpPhone());
                txtEmEmail.setText(newSelection.getEmpEmail());
            }
        });
    }

    private void setCellValueFactory() {
        colEmId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmAge.setCellValueFactory(new PropertyValueFactory<>("empAge"));
        colEmAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colEmContact.setCellValueFactory(new PropertyValueFactory<>("empPhone"));
        colEmEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
    }

    private void loadAllEmployees() {
        ObservableList<Employee> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                Employee tm = new Employee(
                       employee.getEmpId(),
                       employee.getEmpName(),
                       employee.getEmpAge(),
                       employee.getEmpAddress(),
                       employee.getEmpPhone(),
                       employee.getEmpEmail()
                );

                obList.add(tm);
            }

            colEmTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnEmDeleteOnAction(ActionEvent event) {
        String id = txtEmId.getText();
        try {
            boolean isDeleted = EmployeeRepo.delete(id);
            if (isDeleted) {
                Employee selectedEmployee = colEmTel.getSelectionModel().getSelectedItem();
                if (selectedEmployee != null) {
                    colEmTel.getItems().remove(selectedEmployee);
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting employee: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnEmSaveOnAction(ActionEvent event) {
        String id = txtEmId.getText();
        String name = txtEmName.getText();
        String age = txtEmAge.getText();
        String address = txtEmAddress.getText();
        String contact = txtEmContact.getText();
        String email = txtEmEmail.getText();

        Employee employee = new Employee(id, name, age, address, contact, email);
        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                colEmTel.getItems().add(employee);
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving employee: " + e.getMessage()).show();
        }

    }

    @FXML
    void btnEmUpdateOnAction(ActionEvent event) {
        String id = txtEmId.getText();
        String name = txtEmName.getText();
        String age = txtEmAge.getText();
        String address = txtEmAddress.getText();
        String contact = txtEmContact.getText();
        String email = txtEmEmail.getText();

        Employee employee = new Employee(id, name, age, address, contact, email);
        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                Employee selectedItem = colEmTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colEmTel.getItems().indexOf(selectedItem);
                    colEmTel.getItems().set(selectedIndex, employee);
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating employee: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtEmId.clear();
        txtEmName.clear();
        txtEmAge.clear();
        txtEmAddress.clear();
        txtEmContact.clear();
        txtEmEmail.clear();
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        // Implement action for navigating to the home screen
    }

    @FXML
    void btnNextOnAction(ActionEvent event) {
        // Implement action for navigating to the next screen
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        // Implement action for navigating back
    }

    @FXML
    void btnEmCleanOnAction(ActionEvent event) {
        clearFields();
    }
}
