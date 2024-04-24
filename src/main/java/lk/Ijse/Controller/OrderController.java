package lk.Ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import lk.Ijse.Model.CartTm;
import lk.Ijse.Model.Customer;
import lk.Ijse.Model.Products;
import lk.Ijse.Model.ShowRoom;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.OrderRepo;
import lk.Ijse.repository.ProductsRepo;
import lk.Ijse.repository.ShowRoomRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbProductID;

    @FXML
    private ComboBox<String> cmbShowRoomID;

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
    private TableView<CartTm> colOrderTel;
    @FXML
    private Label lblLocationShowRoom;

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
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();


    @FXML
    private TextField txtQty;

    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerIds();
        getCurrentPayId();
        applyButtonAnimations();
        applyLabelAnimations();
        getShowRoomIds();
        getProductIds();
        setCellValueFactory();


        cmbCustomerID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbShowRoomID.requestFocus();
            }
        });

        cmbShowRoomID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbProductID.requestFocus();
            }
        });

        cmbProductID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQty.requestFocus();
            }
        });



        colOrderTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtQty.requestFocus();
            }
        });

    }
    private void setCellValueFactory() {
        colOrPrID.setCellValueFactory(new PropertyValueFactory<>("P_ID"));
        colOrPrDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOrPrUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOrPrQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrPrTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOrAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }
    private void applyButtonAnimations() {
        applyAnimation(btnAddToCart);
        applyAnimation(btnNewCus);
        applyAnimation(btnPlaceOrder);
        applyAnimation(btnPrBack);
        applyAnimation(btnPrBack);
    }

    private void applyLabelAnimations() {
        applyAnimation(lblCustomerName);
        applyAnimation(lblQtyOnHand);
        applyAnimation(lblDescription);
        applyAnimation(lblOrderID);
        applyAnimation(lblPayDate);
        applyAnimation(LblOrderDate);
        applyAnimation(lblUnitPrice);
        applyAnimation(lblPaymentAmount);
        applyAnimation(lblPaymentID);
        applyAnimation(lblLocationShowRoom);



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
    String P_Id = cmbProductID.getValue();
    String Description =lblDescription.getText();
    int Unit_Price = Integer.parseInt(lblUnitPrice.getText());
    int Qty = Integer.parseInt(txtQty.getText());
    int Total_Price = Unit_Price * Qty;

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setOnAction((e) -> {

            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = colOrderTel.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                colOrderTel.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0; i < colOrderTel.getItems().size(); i++) {
            if(P_Id.equals(colOrPrID.getCellData(i))){
                CartTm tm = obList.get(i);
                Qty += tm.getQty();
                Total_Price = Qty*Unit_Price;
                 tm.setQty(Qty);
                 tm.setTotal(Total_Price);

                 colOrderTel.refresh();
                 calculateNetTotal();
                 return;

            }
        }
        CartTm tm = new CartTm(P_Id,Description,Qty,Unit_Price,Total_Price,btnRemove);
        obList.add(tm);

        colOrderTel.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0; i < colOrderTel.getItems().size(); i++) {
            netTotal += (double) colOrPrTotal.getCellData(i);
        }
        lblPaymentAmount.setText(String.valueOf(netTotal));
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
        String id = String.valueOf(cmbProductID.getValue());
        try {
            Products products = ProductsRepo.searchById(id);

            lblDescription.setText(products.getProduct_description());
            lblQtyOnHand.setText(String.valueOf(products.getShowRoom_qtyOnHand()));
            lblUnitPrice.setText(String.valueOf(products.getProduct_unitPrice()));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void getProductIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ProductsRepo.getIds();
            obList.addAll(idList);
            cmbProductID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Products IDs: " + e.getMessage());
        }

    }

    @FXML
    void cmbShowRoomIDOnAction(ActionEvent event) {
        String id = String.valueOf(cmbShowRoomID.getValue());
        try {
            ShowRoom showRoom = ShowRoomRepo.searchById(id);

            lblLocationShowRoom.setText(showRoom.getShowRoomLocation());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getShowRoomIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ShowRoomRepo.getIds();
            obList.addAll(idList);
            cmbShowRoomID.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }
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
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
