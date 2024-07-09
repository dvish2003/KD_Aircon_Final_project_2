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
import lk.Ijse.BO.ProductBO.ProductBO;
import lk.Ijse.BO.ProductShowroomBO.ProductShowRoomJoinBO;
import lk.Ijse.BO.ProductShowroomBO.Product_ShowRoom_BO;
import lk.Ijse.BO.ShowroomBO.ShowRoomBO;
import lk.Ijse.Entity.ShowRoom;
import lk.Ijse.dto.*;
import lk.Ijse.Util.CustomerRegex;
import lk.Ijse.Util.CustomerTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductController {

    @FXML
    private Button btnHome;


    @FXML
    private Button btnPrClean;

    @FXML
    private Button btnPrDelete;



    @FXML
    private Button btnPrSave;

    @FXML
    private Button btnPrUpdate;

    @FXML
    private ComboBox<String> cmbShowRoom;

    @FXML
    private TableColumn<ProductsDTO, String> colPrDesc;

    @FXML
    private TableColumn<ProductsDTO, String> colPrID;

    @FXML
    private TableView<ProductsDTO> colPrTel;

    @FXML
    private TableColumn<ProductsDTO, Integer> colPrUnitPrice;

    @FXML
    private TableColumn<ProductsDTO, Integer> colQtyOnHand;

    @FXML
    private TableColumn<Product_ShowRoomDTO, String> coljoinPrDesc;

    @FXML
    private TableColumn<Product_ShowRoomDTO, String> coljoinPrID;

    @FXML
    private TableColumn<Product_ShowRoomDTO, String> coljoinShowRoom;

    @FXML
    private TableView<ProductShowRoomJoinDTO> colPrJoinTel;

    @FXML
    private TableColumn<Product_ShowRoomDTO, Integer> coljoinQtyOnHand;

    @FXML
    private TableColumn<Product_ShowRoomDTO, Integer> coljoinPrUnitPrice;

    @FXML
    private TextField txtPrDesc;
    @FXML
    private Label lblProductID;


    @FXML
    private TextField txtPrUnitPrice;

    @FXML
    private TextField txtQty;

    private ObservableList<ProductShowRoomJoinDTO> productShowRoomList;

    Animation1 animation1 = new Animation1();

    ShowRoomBO showRoomBO = (ShowRoomBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.ShowRoom);
    ProductShowRoomJoinBO productShowRoomJoinBO = (ProductShowRoomJoinBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.ProductShowRoomJoin);
    Product_ShowRoom_BO productShowRoomBo = (Product_ShowRoom_BO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Product_ShowRoom);
    ProductBO productBo = (ProductBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Products);



    @FXML
    void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        setCellValueFactory2();
        loadAllProductShowRoom();
        applyComboBoxStyles();
        loadAllProduct();
        setListeners();
        getShowRoomIds();
        applyButtonAnimations();

        getProductId();

    }
    private void applyButtonAnimations() {
        animation1.applyAnimation(btnHome);
        animation1.applyAnimation(btnPrClean);
        animation1.applyAnimation(btnPrUpdate);
        animation1.applyAnimation(btnPrSave);
        animation1.applyAnimation(btnPrDelete);
        animation1.addHoverHandlers(btnHome);
        animation1.addHoverHandlers(btnPrClean);
        animation1.addHoverHandlers(btnPrUpdate);
        animation1.addHoverHandlers(btnPrSave);
        animation1.addHoverHandlers(btnPrDelete);


    }



    private void loadAllProduct() throws ClassNotFoundException {
        ObservableList<ProductsDTO> obList = FXCollections.observableArrayList();

        try {
            List<ProductsDTO> productsDTOList = productBo.getAll();
            obList.addAll(productsDTOList);
            colPrTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading products: " + e.getMessage(), e);
        }
    }

    private void loadAllProductShowRoom() throws ClassNotFoundException {
        ObservableList<ProductShowRoomJoinDTO> obList = FXCollections.observableArrayList();

        try {
            List<ProductShowRoomJoinDTO> productsShowRoomList = productShowRoomJoinBO.getAll();
            obList.addAll(productsShowRoomList);
            colPrJoinTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading products: " + e.getMessage(), e);
        }
    }

    private void setCellValueFactory() {
        coljoinShowRoom.setCellValueFactory(new PropertyValueFactory<>("showRoomId"));
        coljoinPrID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        coljoinPrDesc.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        coljoinQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("productQuantityOnHand"));
        coljoinPrUnitPrice.setCellValueFactory(new PropertyValueFactory<>("productUnitPrice"));
    }
    private void setCellValueFactory2() {
        colPrID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colPrDesc.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("showRoom_qtyOnHand"));
        colPrUnitPrice.setCellValueFactory(new PropertyValueFactory<>("product_unitPrice"));
    }

    private void setListeners() {
        setTextFieldFocusTraversal();
        setTableSelectionListener();
    }

    private void setTextFieldFocusTraversal() {


        txtPrDesc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrUnitPrice.requestFocus();
            }
        });

        txtPrUnitPrice.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQty.requestFocus();
            }
        });
    }

    private void setTableSelectionListener() {
        colPrTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblProductID.setText(newSelection.getProduct_id());
                txtPrDesc.setText(newSelection.getProduct_description());
                txtPrUnitPrice.setText(String.valueOf(newSelection.getProduct_unitPrice()));
                txtQty.setText(String.valueOf(newSelection.getShowRoom_qtyOnHand()));
            }
        });
    }

    @FXML
    void cmbShowRoomOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = cmbShowRoom.getValue();
        try {
            ShowRoom showRoom = showRoomBO.searchById(id);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while searching for showroom: " + e.getMessage());
        }
    }

    private void getShowRoomIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = showRoomBO.getIds();
            obList.addAll(idList);
            cmbShowRoom.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching showroom IDs: " + e.getMessage());
        }
    }
    private void getProductId() throws ClassNotFoundException {
        try {
            String currentId = productBo.getCurrentId();

            String nextProductId = generateNextProductID(currentId);
            lblProductID.setText(nextProductId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextProductID(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("PRD");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "PRD" + String.format("%03d", idNum);
        }
        return "PRD001";
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

    public void applyComboBoxStyles() {
        cmbShowRoom.setStyle(" -fx-text-fill: white;");

    }

    @FXML
    void btnPrCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();
    }

    @FXML
    void btnPrDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblProductID.getText();
        try {
            boolean isDeleted = productBo.delete(id);
            if (isDeleted) {
                ProductsDTO selectedItem = colPrTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    colPrTel.getItems().remove(selectedItem);
                    productShowRoomList.removeIf(item -> item.getProductId().equals(id));
                    new Alert(Alert.AlertType.CONFIRMATION, "Product deleted successfully!").show();
                    clearFields();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting product: " + e.getMessage()).show();
        }
    }



    @FXML
    void btnPrSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String showRoomId = cmbShowRoom.getValue();
        String productId = lblProductID.getText();
        String productDescription = txtPrDesc.getText();
        int productUnitPrice = Integer. parseInt(txtPrUnitPrice.getText());
        int productQty = Integer.parseInt(txtQty.getText());

        ProductsDTO productsDTO = new ProductsDTO(productId, productDescription,productQty,productUnitPrice);
        Product_ShowRoomDTO ps = new Product_ShowRoomDTO(productId, showRoomId);

        try {
            if(isValied()){ boolean isProductSaved = productBo.save(productsDTO);
                if (isProductSaved) {
                    colPrTel.getItems().add(productsDTO);
                    boolean isProductShowRoomSaved = productShowRoomBo.save(ps);
                    if (isProductShowRoomSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Product and ShowRoom saved successfully!").show();
                        productShowRoomList.add(new ProductShowRoomJoinDTO(productId, showRoomId, productDescription, productQty, productUnitPrice));
                        clearFields();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Product and ShowRoom!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving data: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String showRoomId = cmbShowRoom.getValue();
        String productId = lblProductID.getText();
        String productDescription = txtPrDesc.getText();
        int productUnitPrice = Integer.parseInt(txtPrUnitPrice.getText());
        int productQty = Integer.parseInt(txtQty.getText());

        ProductsDTO productsDTO = new ProductsDTO(productId, productDescription, productUnitPrice, productQty);
        Product_ShowRoomDTO ps = new Product_ShowRoomDTO(productId, showRoomId);
        try {
            if(isValied()){ boolean isProductUpdate = productBo.update(productsDTO);
                if (isProductUpdate) {
                    ProductsDTO selectedProduct = colPrTel.getSelectionModel().getSelectedItem();
                    selectedProduct.setProduct_description(productDescription);
                    selectedProduct.setProduct_unitPrice(productUnitPrice);
                    selectedProduct.setShowRoom_qtyOnHand(productQty);
                    colPrTel.refresh();

                    boolean isProductShowRoomSaved = productShowRoomBo.save(ps);
                    if (isProductShowRoomSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Product and ShowRoom updated successfully!").show();
                        clearFields();
                        loadAllProductShowRoom();}

                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update Product and ShowRoom!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Product!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating data: " + e.getMessage()).show();
        }
    }


    private void clearFields() throws ClassNotFoundException {
        cmbShowRoom.getSelectionModel().clearSelection();
        txtQty.clear();
        txtPrDesc.clear();
        txtPrUnitPrice.clear();
        getProductId();

    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean isValied(){
        if (!CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtPrUnitPrice)) return false;
        if (!CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtQty)) return false;



        return true;
    }

    public void DescK(KeyEvent keyEvent) {
    }

    public void UnitK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtPrUnitPrice);

    }

    public void QtyK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtQty);

    }


}
