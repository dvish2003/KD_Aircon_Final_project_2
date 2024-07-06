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
import javafx.scene.layout.AnchorPane;
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
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAO;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowRoomController {
    public Label lblRegisterId;
    @FXML
    private  Button btnHome;
    @FXML
    private AnchorPane ShowRoomPane;

    @FXML
    private Button btnSrClean;

    @FXML
    private Button btnSrDelete;



    @FXML
    private Button btnSrSave;

    @FXML
    private Button btnSrUpdate;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colShowRoom;

    @FXML
    private TableView<ShowRoom> colSrTel;



    @FXML
    private TextField txtSrLocation;

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

    @FXML
    void btnPrCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();

    }

    private void clearFields() throws ClassNotFoundException {
        txtSrLocation.clear();
        getCurrentShowRoomID();
    }

    @FXML
    void btnPrDeleteOnAction(ActionEvent event) throws ClassNotFoundException {

        String id = lblRegisterId.getText();
        try {
            boolean isDeleted = showRoomDAO.delete(id);
            if (isDeleted) {
                ShowRoom showRoom = (ShowRoom) colSrTel.getSelectionModel().getSelectedItem();
                if (showRoom != null) {
                    colSrTel.getItems().remove(showRoom);
                    new Alert(Alert.AlertType.CONFIRMATION, "ShowRoom deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete ShowRoom!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting ShowRoom: " + e.getMessage()).show();
        }
    }




    @FXML
    void btnPrSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblRegisterId.getText();
        String location = txtSrLocation.getText();


        ShowRoom showRoom = new ShowRoom(id,location);

        try {
            boolean isSaved = showRoomDAO.save(showRoom);
            if (isSaved) {

                colSrTel.getItems().add(showRoom);
                new Alert(Alert.AlertType.CONFIRMATION, "ShowRoom saved successfully!").show();
                clearFields();
                getCurrentShowRoomID();

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save ShowRoom!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving ShowRoom: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblRegisterId.getText();
        String location = txtSrLocation.getText();


        ShowRoom showRoom = new ShowRoom(id,location);
        try {
            boolean isUpdated = showRoomDAO.update(showRoom);
            if (isUpdated) {
                ShowRoom selectedItem = colSrTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colSrTel.getItems().indexOf(selectedItem);
                    colSrTel.getItems().set(selectedIndex, showRoom);
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
    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllShowRoom();
        getCurrentShowRoomID();
        applyAnimation();


        txtSrLocation.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    btnSrSave.requestFocus();
                }
            });


        colSrTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                lblRegisterId.setText(newSelection.getShowRoomId());
                txtSrLocation.setText(newSelection.getShowRoomLocation());

            }
        });
    }

    private void applyAnimation() {
        animation1.addHoverHandlers(btnSrClean);
        animation1.addHoverHandlers(btnSrUpdate);
        animation1.addHoverHandlers(btnSrDelete);
        animation1.addHoverHandlers(btnSrSave);
        animation1.addHoverHandlers(btnHome);
        animation1.applyAnimation(btnSrClean);
        animation1.applyAnimation(btnSrUpdate);
        animation1.applyAnimation(btnSrDelete);
        animation1.applyAnimation(btnSrSave);
        animation1.applyAnimation(btnHome);
    }


    private void setCellValueFactory() {
        colShowRoom.setCellValueFactory(new PropertyValueFactory<>("showRoomId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("showRoomLocation"));
    }

    private void loadAllShowRoom() throws ClassNotFoundException {
        ObservableList<ShowRoom> obList = FXCollections.observableArrayList();
        try {
            List<ShowRoom> showRoomList = showRoomDAO.getAll();
            for (ShowRoom showRoom : showRoomList) {
                ShowRoom tm = new ShowRoom(
                        showRoom.getShowRoomId(),
                        showRoom.getShowRoomLocation()

                );
                obList.add(tm);
            }
            colSrTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }



    private void getCurrentShowRoomID() throws ClassNotFoundException {
        try {
            String currentId = showRoomDAO.getCurrentId();

            String nextShowRoomID = generateNextShowRoom(currentId);
            lblRegisterId.setText(nextShowRoomID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextShowRoom(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "S" + String.format("%03d", idNum);
        }
        return "S001";

}


public void LocationK(KeyEvent keyEvent) {
      //  CustomerRegex.setTextColor(CustomerTextField.,txtShowRoomID);

    }
}
