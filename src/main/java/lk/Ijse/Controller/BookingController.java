package lk.Ijse.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import lk.Ijse.Model.Customer;
import lk.Ijse.Model.Employee;
import lk.Ijse.Model.Location;
import lk.Ijse.Model.Products;
import lk.Ijse.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookingController {
    @FXML
    private Label LblCustomerName;

    @FXML
    private TableView<?> ColBookTel;

    @FXML
    private Label LblBookingID;

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
    private Button btnUpdate;

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

    public void initialize() {
        setDate();
        getCurrentBookingId();
        getLocationIds();
        getEmployeeIds();
        getCurrentPayId();
        applyButtonAnimations();
        applyLabelAnimations();
       // setCellValueFactory();
      //  loadAllPS();
    }

    private void applyLabelAnimations() {
        applyAnimation(lblPayDate);
        applyAnimation(lblPayDate);
        applyAnimation(lblPaymentAmount);
        applyAnimation(lblPaymentID);
        applyAnimation(LblBookingID);
        applyAnimation(LblEmployeeName);
        applyAnimation(LblLocationAddress);
        applyAnimation(LblPlaceDate);
        applyAnimation(LblCustomerName);



    }

    private void applyButtonAnimations() {
        applyAnimation(btnBook);
        applyAnimation(btnDelete);
        applyAnimation(btnUpdate);
        applyAnimation(btnHome);
        applyAnimation(btnNewLoc);


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

    private void applyAnimation(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), label);
        label.setOnMouseEntered(event -> {
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0.5);
            fadeTransition.play();
        });
        label.setOnMouseExited(event -> {
            fadeTransition.setFromValue(0.5);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        });
    }


    private void getCurrentPayId() {
        try {
            String currentId = OrderRepo.getPayCurrentId();

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
    private void getEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = EmployeeRepo.getIds();
            obList.addAll(idList);
            cmbEmployeeID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }

    }

    private void getLocationIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = LocationRepo.getIds();
            obList.addAll(idList);
            cmbLocationID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }
    }

    private void getCurrentBookingId() {
        try {
            String currentId = OrderRepo.getCurrentId();

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



    @FXML
    void btnBookOnAction(ActionEvent event) {


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewLocOnAction(ActionEvent event) {

    }

    @FXML
    void btnPickDateOnAction(ActionEvent event) {
            // Get the selected location ID
            String locationId = String.valueOf(cmbLocationID.getValue());

            // Get the booked dates for the selected location
            try {
                List<Date> bookedDates = BookingRepo.getBookedDatesForLocation(locationId);

                // Set the day cell factory for the date picker
                btnPickDate.setDayCellFactory(dayCell -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Check if the item (date) is in the list of booked dates
                        if (!empty && item != null && bookedDates.contains(Date.valueOf(item))) {
                            // If the date is booked, set the text color to red
                            setStyle("-fx-text-fill: red;");
                        }
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's error handling strategy
            }
        }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIDOnAction(ActionEvent event) {
        String id = String.valueOf(cmbEmployeeID.getValue());
        try {
            Employee employee = EmployeeRepo.searchById(id);

            LblEmployeeName.setText(employee.getEmpName());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbLocationIDOnAction(ActionEvent event) {
        String id = String.valueOf(cmbLocationID.getValue());
        try {
            Location location = LocationRepo.searchById(id);
            Customer customer = CustomerRepo.searchById(id);
            LblLocationAddress.setText(location.getAddress());
            LblCustomerName.setText(location.getCustomerId());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
