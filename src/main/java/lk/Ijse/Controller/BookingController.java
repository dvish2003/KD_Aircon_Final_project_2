package lk.Ijse.Controller;

import javafx.animation.TranslateTransition;
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
import lk.Ijse.DAO.ShworoomDAO.ShowRoomDAOImpl;
import lk.Ijse.Db.DbConnection;
import lk.Ijse.Model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    @FXML
    private Button btnPrintBill;

    @FXML
    private Label LblCustomerName;

    @FXML
    private TableView<Booking> ColBookTel;

    @FXML
    private Label LblBookingID;

    @FXML
    private AnchorPane BookingPane;


    @FXML
    private Label LblEmployeeName;

    @FXML
    private Label LblLocationAddress;

    @FXML
    private Label LblPlaceDate;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;
    @FXML
    private TextField txtDesc;
    @FXML
    private Button btnNewLoc;

    @FXML
    private DatePicker btnPickDate;



    @FXML
    private ComboBox<String> cmbEmployeeID;

    @FXML
    private ComboBox<String> cmbLocationID;

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
    private Label lblPayDate;

    @FXML
    private Label lblPaymentAmount;

    @FXML
    private Label lblPaymentID;

    Animation1 animation1 = new Animation1();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    CustomerBO customerDAO = new CustomerBOImpl();
    LocationDAO locationDAO = new LocationDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    RegisterDAO registerDAO = new RegisterDAOImpl();
    ShowRoomDAO showRoomDAO = new ShowRoomDAOImpl();
    ProductShowRoomJoinDAO productShowRoomJoinDAO = new ProductShowRoomJoinDAOImpl();
    Product_ShowRoom_DAO productShowRoomDao = new Product_ShowRoom_DAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();
    public void initialize() throws ClassNotFoundException {
        setDate();
        getCurrentBookingId();
        getLocationIds();
        getEmployeeIds();
        getCurrentPayId();
        lblPaymentAmount.setText("2000");
        applyButtonAnimations();
        applyLabelAnimations();
        setCellValueFactory();
        loadAllBooking();

        animation1.addHoverHandlers(btnBook);
        animation1.addHoverHandlers(btnDelete);
        animation1.addHoverHandlers(btnHome);
        animation1.addHoverHandlers(btnNewLoc);
        animation1.addHoverHandlers(btnPrintBill);


        cmbLocationID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbEmployeeID.requestFocus();
            }
        });

        cmbLocationID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnPickDate.requestFocus();
            }
        });

        btnPickDate.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDesc.requestFocus();
            }
        });



        ColBookTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtDesc.requestFocus();
            }
        });

        applyComboBoxStyles();
    }

    private void loadAllBooking() throws ClassNotFoundException {
        ObservableList<Booking> obList = FXCollections.observableArrayList();

        try {
            List<Booking> BookList = bookingDAO.getAll();
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

            ColBookTel.setRowFactory(tv -> new TableRow<Booking>() {   // already expire date colour
                @Override
                protected void updateItem(Booking item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setStyle("-fx-background-color: white;");
                    } else if (item.getBookingDate().toLocalDate().isBefore(LocalDate.now())) {
                        setStyle("-fx-background-color: #70a1ff;");
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

    private void applyLabelAnimations() {animation1.applyAnimation(lblPayDate);
       animation1.applyAnimation(lblPayDate);
       animation1.applyAnimation(lblPaymentAmount);
       animation1.applyAnimation(lblPaymentID);
       animation1.applyAnimation(LblBookingID);
       animation1.applyAnimation(LblEmployeeName);
       animation1.applyAnimation(LblLocationAddress);
       animation1.applyAnimation(LblPlaceDate);
       animation1.applyAnimation(LblCustomerName);
       animation1.applyAnimation(btnPrintBill);

    }

    private void applyButtonAnimations() {
        animation1.applyAnimation(btnBook);
        animation1.applyAnimation(btnDelete);
        animation1.applyAnimation(btnHome);
        animation1.applyAnimation(btnNewLoc);


    }

    private void getCurrentPayId() throws ClassNotFoundException {
        try {
            String currentId = paymentDAO.getCurrentId();

            String nextPayId = generateNextPay(currentId);
            lblPaymentID.setText(nextPayId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextPay(String currentId) {
        if (currentId != null && currentId.startsWith("P")) {
            try {
                int idNum = Integer.parseInt(currentId.substring(1)) + 1;
                return "P" + String.format("%03d", idNum);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid current payment ID format");
            }
        }
        return "P001";
    }
    private void getEmployeeIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = employeeDAO.getIds();
            obList.addAll(idList);
            cmbEmployeeID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }

    }

    private void getLocationIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = locationDAO.getIds();
            obList.addAll(idList);
            cmbLocationID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }
    }
    @FXML
    void cmbEmployeeIDOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = String.valueOf(cmbEmployeeID.getValue());
        try {
            Employee employee = employeeDAO.searchById(id);
            if (employee != null) {
                LblEmployeeName.setText(employee.getEmpName());
            } else {
                LblEmployeeName.setText("Employee not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbLocationIDOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = String.valueOf(cmbLocationID.getValue());
        try {
            Location location = locationDAO.searchById(id);
            if(location != null) {
                LblLocationAddress.setText(location.getAddress());
                LblCustomerName.setText(location.getCustomerId());
            }else{
                LblLocationAddress.setText("Address not found");
                LblCustomerName.setText("Not found  Customer");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getCurrentBookingId() throws ClassNotFoundException {
        try {
            String currentId = bookingDAO.getCurrentId();

            String nextOrderId = generateNextBookingId(currentId);
            LblBookingID.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextBookingId(String currentId) {
            if (currentId != null && currentId.startsWith("B")) {
                String[] split = currentId.split("B");
                int idNum = Integer.parseInt(split[1]);
                idNum++;
                return "B" + String.format("%03d", idNum);
            }
            return "B001";


    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblPayDate.setText(String.valueOf(now));
        LblPlaceDate.setText(String.valueOf(now));
    }


    private boolean checkDate(Date date) throws SQLException {
        String sql = "SELECT * FROM Booking WHERE Booking_Date = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, date);

        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next(); // Returns true if a booking exists for the given date, false otherwise
    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws ClassNotFoundException {
        LocalDate selectedDate = btnPickDate.getValue();

        if (selectedDate == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a booking date.");
            return;
        }

        Date bookingDate = Date.valueOf(selectedDate);

        try {
            if (checkDate(bookingDate)) {
                new Alert(Alert.AlertType.ERROR, "Booking date already exists.").show();
                return; // Exit the method if a booking exists for the selected date
            }

            String bookingId = LblBookingID.getText();
            String empId = cmbEmployeeID.getValue();
            String LocId = cmbLocationID.getValue();
            String paymentId = lblPaymentID.getText();
            Date currentDate = Date.valueOf(LocalDate.now());
            int Amount = Integer.parseInt(lblPaymentAmount.getText());
            String bookingDescription = txtDesc.getText();

            Booking booking = new Booking(bookingId, empId, LocId, paymentId, bookingDate, currentDate, bookingDescription);
            Payment payment = new Payment(paymentId, Amount, currentDate);

            boolean isPaySaved = paymentDAO.save(payment);
            if (isPaySaved) {
                boolean isBookingSave = bookingDAO.save(booking);
                if (isBookingSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Booking placed successfully!").show();
                   // btnPrintBillOnAction(null);
                    loadAllBooking();
                    getCurrentBookingId();
                    getCurrentPayId();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Booking!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Payment!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving data: " + e.getMessage()).show();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid payment amount format!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        ObservableList<Booking> selectedBooking = ColBookTel.getSelectionModel().getSelectedItems();
        if (selectedBooking.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select booking to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected booking?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (Booking booking : selectedBooking) {
                    boolean isDeleted = bookingDAO.delete(booking.getBookingId());
                    if (isDeleted) {
                        ColBookTel.getItems().remove(booking);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete booking: " + booking.getBookingDescription()).show();
                    }
                }
                new Alert(Alert.AlertType.CONFIRMATION, "Customer(s) deleted successfully!").show();
                clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting customer(s): " + e.getMessage()).show();
            }
        }

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnNewLocOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LocationForm.fxml"));
        Parent rootNode = loader.load();
        BookingPane.getChildren().clear();
        BookingPane.getChildren().add(rootNode);
        rootNode.setTranslateX(BookingPane.getWidth());
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), rootNode);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnPickDateOnAction(ActionEvent event) {
        LocalDate selectedDate = btnPickDate.getValue();
        if (selectedDate != null) {
            System.out.println("Selected date: " + selectedDate);
        } else {
            System.out.println("No date selected");
        }
        }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void DescKeyreleseOnAction(KeyEvent event) {

    }
    @FXML
    private void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Reports/KD_Aircon_BookingReport.jrxml");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT\n" +
                "    Booking.Booking_id,\n" +
                "    Employee.Employee_Name,\n" +
                "    Customer.Customer_Name,\n" +
                "    Location.Location_Address,\n" +
                "    Payment.Payment_Amount,\n" +
                "    Booking.Booking_Date\n" +
                "FROM\n" +
                "    Booking\n" +
                "JOIN\n" +
                "    Employee ON Booking.Employee_id = Employee.Employee_id\n" +
                "JOIN\n" +
                "    Location ON Booking.Location_id = Location.Location_id\n" +
                "JOIN\n" +
                "    Payment ON Booking.Payment_id = Payment.Payment_id\n" +
                "JOIN\n" +
                "    Customer ON Location.Customer_id = Customer.Customer_id\n" +
                "WHERE\n" +
                "    Booking.Booking_id = (SELECT MAX(Booking_id) FROM Booking);\n");
        jasperDesign.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, null,DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
    public void applyComboBoxStyles() {
        cmbEmployeeID.setStyle(" -fx-text-fill: white;");
        cmbLocationID.setStyle(" -fx-text-fill: white;");

    }

    private void clearFields() {
        cmbLocationID.getSelectionModel().clearSelection();
        cmbEmployeeID.getSelectionModel().clearSelection();
        LblEmployeeName.setText("");
        LblCustomerName.setText("");
        txtDesc.clear();
        LblLocationAddress.setText("");


    }
}
