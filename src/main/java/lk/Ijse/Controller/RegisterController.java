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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Animation1.Animation1;
import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.CustomerBO.CustomerBOImpl;
import lk.Ijse.DAO.BookingDAO.BookingDAO;
import lk.Ijse.DAO.BookingDAO.BookingDAOImpl;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAO;
import lk.Ijse.DAO.EmployeeDAO.EmployeeDAOImpl;
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
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAO;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;
import lk.Ijse.Entity.Register;
import lk.Ijse.Util.CustomerRegex;
import lk.Ijse.Util.CustomerTextField;
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class   RegisterController {
    @FXML
    private Label lblRegisterId;
    @FXML
    private Label lblRegisterId1;
    @FXML
    private Button SearchBtn;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnHome;

    @FXML
    private Button btnClean;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableView<Register> colRegiTel;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPost;

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();
    CustomerBO customerDAO = new CustomerBOImpl();
    LocationDAO locationDAO = new LocationDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    RegisterDAO registerDAO = new RegisterDAOImpl();
    ShowRoomDAO showRoomDAO = new ShowRoomDAOImpl();
    ProductShowRoomJoinDAO productShowRoomJoinDAO = new ProductShowRoomJoinDAOImpl();
    Product_ShowRoom_DAO productShowRoomDao = new Product_ShowRoom_DAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    Animation1 animation1 = new Animation1();

    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllUsers();
        getCurrentRegister();
        applyAnimation();

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPost.requestFocus();
            }
        });

        txtPost.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPassword.requestFocus();
            }
        });

        colRegiTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblRegisterId1.setText(newSelection.getRegisterId());
                txtName.setText(newSelection.getRegisterName());
                txtPost.setText(newSelection.getPost());
                txtPassword.setText(newSelection.getRegisterPassword());
            }
        });
    }

    private void applyAnimation() {
        animation1.addHoverHandlers(btnClean);
        animation1.addHoverHandlers(btnDelete);
        animation1.addHoverHandlers(btnSave);
        animation1.addHoverHandlers(btnUpdate);
        animation1.addHoverHandlers(btnHome);
        animation1.applyAnimation(btnClean);
        animation1.applyAnimation(btnDelete);
        animation1.applyAnimation(btnSave);
        animation1.applyAnimation(btnUpdate);
        animation1.applyAnimation(btnHome);
    }


    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("registerName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Post"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("registerPassword"));
    }

    private void loadAllUsers() throws ClassNotFoundException {
        ObservableList<Register> obList = FXCollections.observableArrayList();

        try {
            List<Register> userList = registerDAO.getAll();
            obList.addAll(userList);
            colRegiTel.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        Register selectedRegister = colRegiTel.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            try {
                boolean isDeleted = registerDAO.delete(selectedRegister.getRegisterId());
                if (isDeleted) {
                    colRegiTel.getItems().remove(selectedRegister);
                    new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting user: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a user to delete!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblRegisterId.getText();
        String name = txtName.getText();
        String post = txtPost.getText();
        String password = txtPassword.getText();

        Register register = new Register(id, name, post, password);
        System.out.println("is done"+name);

        try {
            boolean isSaved = registerDAO.save(register);
            if (isSaved) {
                colRegiTel.getItems().add(register);
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving user: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        Register selectedRegister = colRegiTel.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            String id = lblRegisterId.getText();
            String name = txtName.getText();
            String post = txtPost.getText();
            String password = txtPassword.getText();

            Register updatedRegister = new Register(id, name, post, password);

            try {
                boolean isUpdated = registerDAO.update(updatedRegister);
                if (isUpdated) {
                    int selectedIndex = colRegiTel.getSelectionModel().getSelectedIndex();
                    colRegiTel.getItems().set(selectedIndex, updatedRegister);
                    new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while updating user: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a user to update!").show();
        }
    }

    private void clearFields() throws ClassNotFoundException {
        getCurrentRegister();
        lblRegisterId1.setText("");
        txtName.clear();
        txtPost.clear();
        txtPassword.clear();

    }

    @FXML
    private void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();    }



    public void NameK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NAME,txtName);

    }

    public void PositionK(KeyEvent keyEvent) {
    }

    public void PasswordK(KeyEvent keyEvent) {
    }

    public void txtSearchKeyRelse(KeyEvent keyEvent) {
    }

    public void SearchBtnOnAction(ActionEvent event) throws ClassNotFoundException {
        String UserID = txtSearch.getText();

        try {
            Register register = registerDAO.searchById(UserID);
            if (register != null) {

                lblRegisterId1.setText(register.getRegisterId());
              //  lblRegisterId1.setText(register.getRegisterId());
                txtPassword.setText(register.getRegisterPassword());
                  txtName.setText(register.getRegisterName());
                    txtPost.setText(register.getPost());


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
    private void getCurrentRegister() throws ClassNotFoundException {
        try {
            String currentId = registerDAO.getCurrentId();

            String nextRegisterID = generateNexRegisterID(currentId);
            lblRegisterId.setText(nextRegisterID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNexRegisterID(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("R");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "R" + String.format("%03d", idNum);
        }
        return "R001";

    }
}

