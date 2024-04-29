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
import javafx.scene.layout.AnchorPane;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent; // Import MouseEvent
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Booking;
import lk.Ijse.repository.BookingRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DashBoardController {

    @FXML
    private TableView<Booking> ColBookTel;

    @FXML
    private AnchorPane SpecialDataPane;

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployye;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLocation;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnShowRoom;

    @FXML
    private TableColumn<?, ?> colBookDate;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colLocID;

    @FXML
    private TableColumn<?, ?> colPayID;

    @FXML
    private TableColumn<?, ?> colPlaceDate;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblProductCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblUserCount;

    @FXML
    private Label lblUserName;

    private int EmployeeCount;
    private int UserCount;
    private int BookingCount;

    @FXML
    private void initialize() {
        setDate();
        setCellValueFactory();
        loadAllBooking();
        addButtonHoverEffect(btnBooking);
        addButtonHoverEffect(btnCustomer);
        addButtonHoverEffect(btnEmployye);
        addButtonHoverEffect(btnHome);
        addButtonHoverEffect(btnLocation);
        addButtonHoverEffect(btnLogOut);
        addButtonHoverEffect(btnOrder);
        addButtonHoverEffect(btnProduct);
        addButtonHoverEffect(btnRegister);
        addButtonHoverEffect(btnShowRoom);
        addButtonHoverEffect(btnShowRoom);
        addButtonHoverEffect(btnShowRoom);

        try {
          EmployeeCount = getEmployeeCount();
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        try {
            UserCount = getUserCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
       try {
           BookingCount = getBookingCount();
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
        setBookingCount(BookingCount);
        setEmployeeCount(EmployeeCount);
       setUserCount(UserCount);
    }

    private void setUserCount(int UserCount) {
        lblUserCount.setText(String.valueOf(UserCount));

    }

    private int getUserCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS User_count FROM Register";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("User_count");
        }
        return 0;
    }

    private void setEmployeeCount(int EmployeeCount) {
        lblEmployeeCount.setText(String.valueOf(EmployeeCount));
    }

    private int getEmployeeCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Employee_count FROM Employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Employee_count");
        }
        return 0;
    }


    private void setBookingCount(int BookingCount) {
        lblProductCount.setText(String.valueOf(BookingCount));
    }

    private int getBookingCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Booking_count FROM Booking";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Booking_count");
        }
        return 0;
    }


    private void loadAllBooking() {
        ObservableList<Booking> obList = FXCollections.observableArrayList();

        try {
            List<Booking> BookList = BookingRepo.getAll();
            for (Booking booking : BookList) {
                Booking tm = new Booking(
                        booking.getBookingId(),
                        booking.getLocId(),
                        booking.getEmpId(),
                        booking.getPaymentId(),
                        booking.getBookingDate(),
                        booking.getPlaceDate(),
                        booking.getBookingDescription()
                );

                obList.add(tm);
            }

            ColBookTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("LocId"));
        colLocID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colPayID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPlaceDate.setCellValueFactory(new PropertyValueFactory<>("PlaceDate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("bookingDescription"));
        colBookDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));

    }


    private void addButtonHoverEffect(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), button);
        button.setOnMouseEntered((MouseEvent event) -> {
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        button.setOnMouseExited((MouseEvent event) -> {
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Booking.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerForm.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnEmployyeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeForm.fxml"));
        Parent rootNode = loader.load();   FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnLocationOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LocationForm.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LognForm.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Order.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Product.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Register_form.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }

    @FXML
    void btnShowRoomOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ShowRoom.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }

}
