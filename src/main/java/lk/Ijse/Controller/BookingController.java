package lk.Ijse.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import lk.Ijse.Model.*;
import lk.Ijse.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookingController {
    @FXML
    private Label LblCustomerName;

    @FXML
    private TableView<Booking> ColBookTel;

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
        lblPaymentAmount.setText("2000");
        applyButtonAnimations();
        applyLabelAnimations();
       setCellValueFactory();
       loadAllBooking();

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
            String currentId = BookingRepo.getCurrentId();

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
        String bookingId = LblBookingID.getText();
        String empId = cmbEmployeeID.getValue();
        String LocId = cmbLocationID.getValue();
        String paymentId = lblPaymentID.getText();

        LocalDate selectedDate = btnPickDate.getValue();
        if (selectedDate == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a booking date.");
            return;
        }

        Date bookingDate = Date.valueOf(selectedDate);

        Date currentDate = Date.valueOf(LocalDate.now());

        int Amount = Integer.parseInt(lblPaymentAmount.getText());
        String bookingDescription = txtDesc.getText();

        Booking booking = new Booking(bookingId, empId, LocId, paymentId, bookingDate, currentDate, bookingDescription);
        Payment payment = new Payment(paymentId, Amount, currentDate);

        try {
                boolean isPaySaved = PaymentRepo.save(payment);
                             if (isPaySaved) {
                                 boolean isBookingSave = BookingRepo.save(booking);

                                 if (isBookingSave) {
                                 new Alert(Alert.AlertType.CONFIRMATION, "Booking placed successfully!").show();
                 loadAllBooking();
                   getCurrentBookingId();
                   getCurrentPayId();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Booking!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Booking!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving data: " + e.getMessage()).show();
        }
    }

    private void clearFields() {

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
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
                    boolean isDeleted = BookingRepo.delete(booking.getBookingId());
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
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewLocOnAction(ActionEvent event) {

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


        @FXML
    void btnUpdateOnAction(ActionEvent event) {
            Booking selectedBooking = ColBookTel.getSelectionModel().getSelectedItem();

            if (selectedBooking == null) {
                showAlert(Alert.AlertType.ERROR, "Please select a booking to update.");
                return;
            }
            String empId = cmbEmployeeID.getValue();
            String locId = cmbLocationID.getValue();
            String bookingDescription = txtDesc.getText();
            LocalDate selectedDate = btnPickDate.getValue();
            Date bookingDate = Date.valueOf(selectedDate);

            selectedBooking.setEmpId(empId);
            selectedBooking.setLocId(locId);
            selectedBooking.setBookingDescription(bookingDescription);
            selectedBooking.setBookingDate(bookingDate);

            try {
                boolean isUpdated = BookingRepo.update(selectedBooking);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Booking updated successfully!").show();
                    loadAllBooking();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update booking!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while updating booking: " + e.getMessage()).show();
            }
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
