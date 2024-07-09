package lk.Ijse.Controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Animation1.Animation1;
import lk.Ijse.BO.BOFactory;
import lk.Ijse.BO.CustomerBO.CustomerBO;
import lk.Ijse.BO.LocationBO.LocationBO;
import lk.Ijse.Entity.Customer;
import lk.Ijse.dto.CustomerDTO;
import lk.Ijse.dto.LocationDTO;
import lk.Ijse.Util.CustomerRegex;
import lk.Ijse.Util.CustomerTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LocationController {

    @FXML
    private AnchorPane LocationAncorPane;
    @FXML
    private Label lblLocationId;

    @FXML
    private Button btnLocClean;

    @FXML
    private Button btnLocDelete;

    @FXML
    private Button btnLocHome;


    @FXML
    private Button btnLocSave;

    @FXML
    private Button btnLocUpdate;

    @FXML
    private Button btnNewCus;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<LocationDTO, String> colId;

    @FXML
    private TableColumn<LocationDTO, String> colProvince;

    @FXML
    private TableColumn<LocationDTO, String> colCity;

    @FXML
    private TableColumn<LocationDTO, String> colAddress;

    @FXML
    private TableColumn<LocationDTO, String> colZipCode;

    @FXML
    private TableColumn<LocationDTO, String> colCu_ID;

    @FXML
    private TableView<LocationDTO> colLoTel;

    @FXML
    private TextField txtLoZip;

    @FXML
    private TextField txtLoAddress;

    @FXML
    private TextField txtLoCity;

    @FXML
    private TextField txtLoId;

    @FXML
    private TextField txtLoProvince;


    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Customer);
    LocationBO locationBO = (LocationBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Location);

    Animation1 animation1 = new Animation1();


    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllLocation();
        getCustomerIds();
        applyButtonAnimations();
        getCurrentId();

        applyComboBoxStyles();



        txtLoProvince.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLoCity.requestFocus();
            }
        });

        txtLoCity.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLoAddress.requestFocus();
            }
        });
        txtLoAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLoZip.requestFocus();
            }
        });
        txtLoZip.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLocSave.requestFocus();
            }
        });

        colLoTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cmbCustomerId.setValue(newSelection.getCustomerId());
                lblLocationId.setText(newSelection.getId());
                txtLoProvince.setText(newSelection.getProvince());
                txtLoCity.setText(newSelection.getCity());
                txtLoAddress.setText(newSelection.getAddress());
                txtLoZip.setText(newSelection.getZipCode());
            }
        });
    }
    private void applyButtonAnimations() {
       animation1. applyAnimation(btnNewCus);
       animation1. applyAnimation(btnLocClean);
       animation1. applyAnimation(btnLocDelete);
       animation1. applyAnimation(btnLocSave);
       animation1. applyAnimation(btnLocUpdate);
       animation1. applyAnimation(btnLocHome);
        animation1.addHoverHandlers(btnNewCus);
        animation1.addHoverHandlers(btnLocClean);
        animation1.addHoverHandlers(btnLocDelete);
        animation1.addHoverHandlers(btnLocSave);
        animation1.addHoverHandlers(btnLocUpdate);
        animation1.addHoverHandlers(btnLocHome);

    }

    private void setCellValueFactory() {
        colCu_ID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colZipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
    }

    private void loadAllLocation() throws ClassNotFoundException {
        try {
            List<LocationDTO> locationDTOList = locationBO.getAll();
            ObservableList<LocationDTO> obList = FXCollections.observableArrayList(locationDTOList);
            colLoTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading locations: " + e.getMessage(), e);
        }
    }

    private void getCustomerIds() throws ClassNotFoundException {
        try {
            List<String> idList = customerBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnLocCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();
    }

    @FXML
    void btnLocDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        ObservableList<LocationDTO> selectedLocationDTOS = colLoTel.getSelectionModel().getSelectedItems();
        if (selectedLocationDTOS.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select location(s) to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected location(s)?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (LocationDTO locationDTO : selectedLocationDTOS) {
                    boolean isDeleted = locationBO.delete(locationDTO.getId());
                    if (isDeleted) {
                        colLoTel.getItems().remove(locationDTO);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete location: " + locationDTO.getId()).show();
                    }
                }
                new Alert(Alert.AlertType.CONFIRMATION, "Location(s) deleted successfully!").show();
                clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting location(s): " + e.getMessage()).show();
            }
        }
    }


    @FXML
    void btnLocHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }



    @FXML
    void btnLocSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String cuId = cmbCustomerId.getValue();
        String loId = lblLocationId.getText();
        String loProvince = txtLoProvince.getText();
        String loCity = txtLoCity.getText();
        String loAddress = txtLoAddress.getText();
        String loZipCode = txtLoZip.getText();

        LocationDTO locationDTO = new LocationDTO(cuId, loId, loProvince, loCity, loAddress, loZipCode);

        try {
            if(isValied()){ boolean isSaved = locationBO.save(locationDTO);
                if (isSaved) {
                    colLoTel.getItems().add(locationDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "Location saved successfully!").show();
                    clearFields();}

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save location!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving location: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnLocUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String cuId = cmbCustomerId.getValue();
        String loId = lblLocationId.getText();
        String loProvince = txtLoProvince.getText();
        String loCity = txtLoCity.getText();
        String loAddress = txtLoAddress.getText();
        String loZipCode = txtLoZip.getText();

        LocationDTO locationDTO = new LocationDTO(cuId, loId, loProvince, loCity, loAddress, loZipCode);

        try {
            if(isValied()){boolean isUpdated = locationBO.update(locationDTO);
                if (isUpdated) {
                    int selectedIndex = colLoTel.getSelectionModel().getSelectedIndex();
                    colLoTel.getItems().set(selectedIndex, locationDTO);
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Location updated successfully!").show();}

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update location!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating location: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnNewCusOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerForm.fxml"));
        Parent rootNode = loader.load();
        LocationAncorPane.getChildren().clear();
        LocationAncorPane.getChildren().add(rootNode);
        rootNode.setTranslateX(LocationAncorPane.getWidth());
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), rootNode);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = cmbCustomerId.getValue();
        try {
            Customer customer = customerBO.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void applyComboBoxStyles() {
        cmbCustomerId.setStyle(" -fx-text-fill: white;");

    }

    private void clearFields() throws ClassNotFoundException {
        cmbCustomerId.getSelectionModel().clearSelection();
        txtLoProvince.clear();
        txtLoCity.clear();
        txtLoAddress.clear();
        txtLoZip.clear();
        getCurrentId();

    }

    @FXML
    private void ProvinceK(KeyEvent keyEvent) {
      //  CustomerRegex.setTextColor(CustomerTextField.NAME,txtLoProvince);

    }
    @FXML
    private void CityK(KeyEvent keyEvent) {
     //   CustomerRegex.setTextColor(CustomerTextField.NAME,txtLoCity);

    }
    public boolean isValied(){
        if (!CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtLoZip)) return false;


        return true;
    }

    @FXML
    private void ZipCodeK(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.NUMBER,txtLoZip);


    }

  @FXML
    private  void AddressK(KeyEvent keyEvent){

  }

    private void getCurrentId() throws ClassNotFoundException {
        try {
            String currentId = locationBO.getCurrentId();

            String nextOrderId = generateNextLocationId(currentId);
            lblLocationId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextLocationId(String currentId) {
        if (currentId != null && currentId.startsWith("L")) {
            String[] split = currentId.split("L");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "L" + String.format("%03d", idNum);
        }
        return "L001";


    }

}
