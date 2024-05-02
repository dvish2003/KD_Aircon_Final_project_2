package lk.Ijse.Controller;

import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;
import lk.Ijse.Model.Employee;
import lk.Ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {



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
        applyButtonAnimations();

        addHoverHandlers(btnEmClean);
        addHoverHandlers(btnEmDelete);
        addHoverHandlers(btnEmSave);
        addHoverHandlers(btnEmUpdate);
        addHoverHandlers(btnHome);

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

        txtEmEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmContact.requestFocus();
            }


        });
        txtEmContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnEmSave.requestFocus();
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
    private void addHoverHandlers(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #27f802; -fx-text-fill: white;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        });
    }
    private void applyButtonAnimations() {
        applyAnimation(btnEmClean);
        applyAnimation(btnEmDelete);
        applyAnimation(btnEmSave);
        applyAnimation(btnEmUpdate);
        applyAnimation(btnHome);

    }



    private void applyAnimation(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), button);
        button.setOnMouseEntered(event -> {
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        button.setOnMouseExited(event -> {
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
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
        ObservableList<Employee> selectedEmployees = colEmTel.getSelectionModel().getSelectedItems();
        if (selectedEmployees.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select employee(s) to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected employee(s)?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (Employee employee : selectedEmployees) {
                    boolean isDeleted = EmployeeRepo.delete(employee.getEmpId());
                    if (isDeleted) {
                        colEmTel.getItems().remove(employee);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete employee: " + employee.getEmpName()).show();
                    }
                }
                new Alert(Alert.AlertType.CONFIRMATION, "Employee(s) deleted successfully!").show();
                clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting employee(s): " + e.getMessage()).show();
            }
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
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();    }


    @FXML
    void btnEmCleanOnAction(ActionEvent event) {
        clearFields();
    }
}
