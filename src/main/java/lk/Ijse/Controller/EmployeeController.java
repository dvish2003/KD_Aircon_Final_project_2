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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.Ijse.Animation1.Animation1;
import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.CustomerBO.CustomerBOImpl;
import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAO;
import lk.Ijse.DAO.LocationDAO.LocationDAO;
import lk.Ijse.DAO.LocationDAO.LocationDAOImpl;
import lk.Ijse.DAO.OrderDAO.OrderDAO;
import lk.Ijse.DAO.OrderDAO.OrderDAOImpl;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAO;
import lk.Ijse.DAO.OrderDAO.OrderDetailDAOImpl;
import lk.Ijse.DAO.PaymentDAO.PaymentDAO;
import lk.Ijse.DAO.PaymentDAO.PaymentDAOImpl;
import lk.Ijse.DAO.ProductDAO.ProductDAO;
import lk.Ijse.DAO.ProductDAO.ProductDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAO;
import lk.Ijse.DAO.ProductShowroomDAO.ProductShowRoomJoinDAOImpl;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAO;
import lk.Ijse.DAO.ProductShowroomDAO.Product_ShowRoom_DAOImpl;
import lk.Ijse.DAO.RegisterDAO.RegisterDAO;
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAO;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;
import lk.Ijse.Entity.Employee;
import lk.Ijse.Util.CustomerRegex;
import lk.Ijse.Util.CustomerTextField;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {



    @FXML
    private Button btnEmClean;

    @FXML
    private Button btnEmDelete;

    @FXML
    private Button SearchBtn;

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
    private TextField txtEmName;
    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblEmployeeAuto;

    @FXML
    private TextField txtSearch;

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();
    CustomerBO customerDAO = new CustomerBOImpl();
    LocationDAO locationDAO = new LocationDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    RegisterDAO registerDAO = new RegisterDAOImpl();
    ShowRoomDAO  showRoomDAO = new ShowRoomDAOImpl();
    ProductShowRoomJoinDAO productShowRoomJoinDAO = new ProductShowRoomJoinDAOImpl();
    Product_ShowRoom_DAO productShowRoomDao = new Product_ShowRoom_DAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();

    Animation1 animation1 = new Animation1();

    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllEmployees();
        getCurrentEmployeeID();

        applyAnimation();


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
                txtEmEmail.requestFocus();
            }


        });
        txtEmEmail  .setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnEmSave.requestFocus();
            }


        });


        colEmTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblEmployeeAuto.setText(newSelection.getEmpId());
                txtEmName.setText(newSelection.getEmpName());
                txtEmAge.setText(newSelection.getEmpAge());
                txtEmAddress.setText(newSelection.getEmpAddress());
                txtEmContact.setText(newSelection.getEmpPhone());
                txtEmEmail.setText(newSelection.getEmpEmail());
            }
        });
    }

    private void applyAnimation() {
        animation1.addHoverHandlers(btnEmClean);
        animation1.addHoverHandlers(btnEmDelete);
        animation1.addHoverHandlers(btnEmSave);
        animation1.addHoverHandlers(btnEmUpdate);
        animation1.addHoverHandlers(btnHome);
        animation1.applyAnimation(btnEmClean);
        animation1.applyAnimation(btnEmDelete);
        animation1.applyAnimation(btnEmSave);
        animation1.applyAnimation(btnEmUpdate);
        animation1.applyAnimation(btnHome);
    }


    private void setCellValueFactory() {
        colEmId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmAge.setCellValueFactory(new PropertyValueFactory<>("empAge"));
        colEmAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colEmContact.setCellValueFactory(new PropertyValueFactory<>("empPhone"));
        colEmEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
    }

    private void loadAllEmployees() throws ClassNotFoundException {
        ObservableList<Employee> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = employeeDAO.getAll();
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
    void btnEmDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
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
                    boolean isDeleted = employeeDAO.delete(employee.getEmpId());
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
    void btnEmSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblEmployeeAuto.getText();
        String name = txtEmName.getText();
        String age = txtEmAge.getText();
        String address = txtEmAddress.getText();
        String contact = txtEmContact.getText();
        String email = txtEmEmail.getText();

        Employee employee = new Employee(id, name, age, address, contact, email);
        try {
            if(isValied()){boolean isSaved = employeeDAO.save(employee);
                if (isSaved) {
                    colEmTel.getItems().add(employee);
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved successfully!").show();
                    getCurrentEmployeeID();
                    clearFields();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving employee: " + e.getMessage()).show();
        }

    }

    @FXML
    void btnEmUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblEmployeeAuto.getText();
        String name = txtEmName.getText();
        String age = txtEmAge.getText();
        String address = txtEmAddress.getText();
        String contact = txtEmContact.getText();
        String email = txtEmEmail.getText();

        Employee employee = new Employee(id, name, age, address, contact, email);
        try {
            if(isValied()){ boolean isUpdated = employeeDAO.update(employee);
                if (isUpdated) {
                    Employee selectedItem = colEmTel.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        int selectedIndex = colEmTel.getItems().indexOf(selectedItem);
                        colEmTel.getItems().set(selectedIndex, employee);
                        new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
                        clearFields();
                    }
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating employee: " + e.getMessage()).show();
        }
    }

    private void clearFields() throws ClassNotFoundException {
lblEmployee.setText("");
        txtEmName.clear();
        txtEmAge.clear();
        txtEmAddress.clear();
        txtEmContact.clear();
        txtEmEmail.clear();
        getCurrentEmployeeID();

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

    public boolean isValied(){
        if (!CustomerRegex.setTextColor(CustomerTextField.NAME,txtEmName)) return false;
        if (!CustomerRegex.setTextColor(CustomerTextField.CONTACT,txtEmContact)) return false;
        if (!CustomerRegex.setTextColor(CustomerTextField.EMAIL,txtEmEmail)) return false;
        if (!CustomerRegex.setTextColor(CustomerTextField.ADDRESS,txtEmAddress)) return false;
        if (!CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtEmAge)) return false;

        return true;
    }
    @FXML
    void btnEmCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();
    }



    public void NameK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NAME,txtEmName);

    }

    public void AddressK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.ADDRESS,txtEmAddress);

    }

    public void ContactK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.CONTACT,txtEmContact);

    }

    public void EmailK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.EMAIL,txtEmEmail);

    }

    public void AgeK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtEmAge);


    }

    public void txtSearchKeyRelse(KeyEvent keyEvent) {
          CustomerRegex.setTextColor2(CustomerTextField.ID,txtSearch);

    }

    @FXML
    void SearchBtnOnAction(ActionEvent event) throws ClassNotFoundException {
        String EmployeeID = txtSearch.getText();

        try {
            Employee employee = employeeDAO.searchById(EmployeeID);
            if (employee != null) {
                lblEmployee.setText(employee.getEmpId());
                txtEmName.setText(employee.getEmpName());
                txtEmContact.setText(employee.getEmpPhone());
                txtEmEmail.setText(employee.getEmpEmail());
                txtEmAge.setText(employee.getEmpAge());
                txtEmEmail.setText(employee.getEmpEmail());
                txtEmAddress.setText(employee.getEmpAddress());

            } else {
                showAlert(Alert.AlertType.ERROR, "Employee not found.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Employee: " + e.getMessage());
        }
    }
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void getCurrentEmployeeID() throws ClassNotFoundException {
        try {
            String currentId = employeeDAO.getCurrentId();

            String nextEmployeeID = generateNextEmployeeId(currentId);
            lblEmployeeAuto.setText(nextEmployeeID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextEmployeeId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "E" + String.format("%03d", idNum);
        }
        return "E001";
    }
}
