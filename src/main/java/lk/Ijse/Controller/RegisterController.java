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
import lk.Ijse.BO.BOFactory;
import lk.Ijse.BO.BookingBO.BookingBO;
import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.EmployeeBO.EmployeeBO;
import lk.Ijse.BO.LocationBO.LocationBO;
import lk.Ijse.BO.OrderBO.OrderBO;
import lk.Ijse.BO.OrderBO.OrderDetailBO;
import lk.Ijse.BO.PaymentBO.PaymentBO;
import lk.Ijse.BO.ProductBO.ProductBO;
import lk.Ijse.BO.ProductShowroomBO.ProductShowRoomJoinBO;
import lk.Ijse.BO.ProductShowroomBO.Product_ShowRoom_BO;
import lk.Ijse.BO.RegisterBO.RegisterBO;
import lk.Ijse.Entity.Register;
import lk.Ijse.dto.RegisterDTO;
import lk.Ijse.Util.CustomerRegex;
import lk.Ijse.Util.CustomerTextField;

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
    private TableView<RegisterDTO> colRegiTel;

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

   // ShowRoomBO showRoomBO = (ShowRoomBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Booking);
    ProductShowRoomJoinBO productShowRoomJoinBO = (ProductShowRoomJoinBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.ProductShowRoomJoin);
    Product_ShowRoom_BO productShowRoomBo = (Product_ShowRoom_BO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Product_ShowRoom);
    ProductBO productBo = (ProductBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Products);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Customer);
    LocationBO locationBO = (LocationBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Location);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Order);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Payment);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.OrderDetail);
    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Register);
    BookingBO bookingBO = (BookingBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Booking);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Employee);
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
                lblRegisterId.setText(newSelection.getRegisterId());
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
        ObservableList<RegisterDTO> obList = FXCollections.observableArrayList();

        try {
            List<RegisterDTO> userList = registerBO.getAll();
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
        RegisterDTO selectedRegisterDTO = colRegiTel.getSelectionModel().getSelectedItem();
        if (selectedRegisterDTO != null) {
            try {
                boolean isDeleted = registerBO.delete(selectedRegisterDTO.getRegisterId());
                if (isDeleted) {
                    colRegiTel.getItems().remove(selectedRegisterDTO);
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

        RegisterDTO registerDTO = new RegisterDTO(id, name, post, password);
        System.out.println("is done"+name);

        try {
            boolean isSaved = registerBO.save(registerDTO);
            if (isSaved) {
                colRegiTel.getItems().add(registerDTO);
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
        RegisterDTO selectedRegisterDTO = colRegiTel.getSelectionModel().getSelectedItem();
        if (selectedRegisterDTO != null) {
            String id = lblRegisterId.getText();
            String name = txtName.getText();
            String post = txtPost.getText();
            String password = txtPassword.getText();

            RegisterDTO updatedRegisterDTO = new RegisterDTO(id, name, post, password);

            try {
                boolean isUpdated = registerBO.update(updatedRegisterDTO);
                if (isUpdated) {
                    int selectedIndex = colRegiTel.getSelectionModel().getSelectedIndex();
                    colRegiTel.getItems().set(selectedIndex, updatedRegisterDTO);
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
            Register register = registerBO.searchById(UserID);
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
            String currentId = registerBO.getCurrentId();

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

