package lk.Ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.Ijse.Model.Customer;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.OrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderController {

    @FXML
    private Label LblOrderDate;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnNewCus;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnPrBack;

    @FXML
    private Button btnPrNext;

    @FXML
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<?> cmbProductID;

    @FXML
    private ComboBox<?> cmbShowRoomID;

    @FXML
    private TableColumn<?, ?> colOrAction;

    @FXML
    private TableColumn<?, ?> colOrPrDesc;

    @FXML
    private TableColumn<?, ?> colOrPrID;

    @FXML
    private TableColumn<?, ?> colOrPrQty;

    @FXML
    private TableColumn<?, ?> colOrPrTotal;

    @FXML
    private TableColumn<?, ?> colOrPrUnitPrice;

    @FXML
    private TableColumn<?, ?> colOrQtyOnHand;

    @FXML
    private TableView<?> colOrderTel;

    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblPayDate;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblPaymentAmount;

    @FXML
    private Label lblPaymentID;


    @FXML
    private TextField txtQty;

    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerIds();
        getCurrentPayId();
    }

    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbCustomerID.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            lblOrderID.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
            int idNum = Integer.parseInt(split[1]);
            idNum++; // Increment the ID number
            return "O" + String.format("%03d", idNum);
        }
        return "O001";
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        lblPayDate.setText(String.valueOf(now));
        LblOrderDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCusOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrNextOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIDOnAction(ActionEvent event) {
        String id = cmbCustomerID.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);

            lblCustomerName.setText(customer.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbProductIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowRoomIDOnAction(ActionEvent event) {

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


}
