package lk.Ijse.Controller;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.Booking;
import lk.Ijse.repository.BookingRepo;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {


    @FXML
    private BarChart<String,Integer> BarChart;

    @FXML
    private Label lblOrderCount;

    @FXML
    private TableView<Booking> ColBookTel;

    @FXML
    public AnchorPane SpecialDataPane;

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployye;



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
    private Label lblTitle;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblProductCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblUserCount;



    private String fullTitle ="Welcome To KD.Aircon System";



    private int currentIndex = 0;

    private int EmployeeCount;

    private int UserCount;

    private int BookingCount;

    private int OrderCount;


public void Initialize(){
    loadOrderData();
    animateLabel();
    setDate();
    setCellValueFactory();
    loadAllBooking();
    addHoverHandlers(btnBooking);
    addHoverHandlers(btnCustomer);
    addHoverHandlers(btnEmployye);
    addHoverHandlers(btnLocation);
    addHoverHandlers(btnLogOut);
    addHoverHandlers(btnOrder);
    addHoverHandlers(btnProduct);
    addHoverHandlers(btnRegister);
    addHoverHandlers(btnShowRoom);

    try {
        EmployeeCount = getEmployeeCount();
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }

    try {
        OrderCount = getOrderCount();
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
    setOrderCount(OrderCount);
}


    private void addHoverHandlers(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #27f802; -fx-text-fill: white;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        });
    }
    private void setOrderCount(int UserCount) {
        lblOrderCount.setText(String.valueOf(UserCount));

    }
    private int getOrderCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS Order_count FROM `Order`";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("Order_count");
        }
        return 0;
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

    private void loadOrderData() {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT MONTH(OrderPlaceDate_Date) AS Month, COUNT(*) AS Orders FROM `Order` GROUP BY MONTH(OrderPlaceDate_Date)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String month = resultSet.getString("Month");
                int orders = resultSet.getInt("Orders");
                series.getData().add(new XYChart.Data<>(month, orders));
            }
            BarChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            ColBookTel.setRowFactory(tv -> new TableRow<Booking>() {
                @Override
                protected void updateItem(Booking item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setStyle("-fx-background-color: white;");
                    } else if (item.getBookingDate().toLocalDate().isBefore(LocalDate.now())) {
                        setStyle("-fx-background-color: #27f802;;");
                    } else {
                        setStyle("");
                    }
                }
            });
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




    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Booking.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerForm.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }

    @FXML
    void btnEmployyeOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeForm.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }



    @FXML
    void btnLocationOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LocationForm.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LognForm.fxml"));
        Parent rootNode = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(rootNode));

        newStage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Order.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Product.fxml"));
        Parent rootNode = loader.load();
        SpecialDataPane.getChildren().clear();
        SpecialDataPane.getChildren().add(rootNode);
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

    private void animateLabel() {

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.2),
                event -> {
                    if (currentIndex <= fullTitle.length()) {
                        lblTitle.setText(fullTitle.substring(0, currentIndex));
                        currentIndex++;
                    }
                }
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(fullTitle.length() + 1);

        timeline.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("January", 2323));
        series1.getData().add(new XYChart.Data("February", 43333));
        series1.getData().add(new XYChart.Data("March", 35000));
        series1.getData().add(new XYChart.Data("April", 10000));
        series1.getData().add(new XYChart.Data("May", 12000));
        series1.getData().add(new XYChart.Data("June", 2323));
        series1.getData().add(new XYChart.Data("July", 43333));
        series1.getData().add(new XYChart.Data("August", 35000));
        series1.getData().add(new XYChart.Data("September", 10000));
        series1.getData().add(new XYChart.Data("October", 12000));
        series1.getData().add(new XYChart.Data("November", 10000));
        series1.getData().add(new XYChart.Data("December", 12000));

        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data("January", 23223));
        series2.getData().add(new XYChart.Data("February", 433233));
        series2.getData().add(new XYChart.Data("March", 35000));
        series2.getData().add(new XYChart.Data("April", 110000));
        series2.getData().add(new XYChart.Data("May", 122000));
        series2.getData().add(new XYChart.Data("June", 23223));
        series2.getData().add(new XYChart.Data("July", 433233));
        series2.getData().add(new XYChart.Data("August", 35000));
        series2.getData().add(new XYChart.Data("September", 110000));
        series2.getData().add(new XYChart.Data("October", 122000));
        series2.getData().add(new XYChart.Data("November", 110000));
        series2.getData().add(new XYChart.Data("December", 122000));

        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data("January", 23223));
        series3.getData().add(new XYChart.Data("February", 433233));
        series3.getData().add(new XYChart.Data("March", 35000));
        series3.getData().add(new XYChart.Data("April", 110000));
        series3.getData().add(new XYChart.Data("May", 122000));
        series3.getData().add(new XYChart.Data("June", 23223));
        series3.getData().add(new XYChart.Data("July", 433233));
        series3.getData().add(new XYChart.Data("August", 35000));
        series3.getData().add(new XYChart.Data("September", 110000));
        series3.getData().add(new XYChart.Data("October", 122000));
        series3.getData().add(new XYChart.Data("November", 110000));
        series3.getData().add(new XYChart.Data("December", 122000));
        Initialize();
        BarChart.getData().addAll(series1, series2, series3);


    }

}





