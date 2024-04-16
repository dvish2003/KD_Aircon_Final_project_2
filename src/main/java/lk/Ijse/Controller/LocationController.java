package lk.Ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;
import lk.Ijse.List.CustomerLocation;
import lk.Ijse.Model.Customer;
import lk.Ijse.Model.Location;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.LocationRepo;

import java.sql.SQLException;
import java.util.List;

public class LocationController {

    @FXML
    private Button btnLocBack;

    @FXML
    private Button btnLocClean;

    @FXML
    private Button btnLocDelete;

    @FXML
    private Button btnLocHome;

    @FXML
    private Button btnLocNext;

    @FXML
    private Button btnLocSave;

    @FXML
    private Button btnLocUpdate;

    @FXML
    private Button btnNewCus;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<Location, String> colId;

    @FXML
    private TableColumn<Location, String> colProvince;

    @FXML
    private TableColumn<Location, String> colCity;

    @FXML
    private TableColumn<Location, String> colAddress;

    @FXML
    private TableColumn<Location, String> colZipCode;

    @FXML
    private TableColumn<Location, String> colCu_ID;

    @FXML
    private TableView<Location> colLoTel;

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

    public void initialize() {
        setCellValueFactory();
        loadAllLocation();
        getCustomerIds();

        txtLoId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLoProvince.requestFocus();
            }
        });

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

        colLoTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cmbCustomerId.setValue(newSelection.getId());
                txtLoId.setText(newSelection.getId());
                txtLoProvince.setText(newSelection.getProvince());
                txtLoCity.setText(newSelection.getCity());
                txtLoAddress.setText(newSelection.getAddress());
                txtLoZip.setText(newSelection.getZipCode());
            }
        });
    }

    private void setCellValueFactory() {
        colCu_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
        colId.setCellValueFactory(new PropertyValueFactory<>("Location_id"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Location_Province"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("Location_City"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Location_Address"));
        colZipCode.setCellValueFactory(new PropertyValueFactory<>("Location_ZipCode"));

    }

    private void loadAllLocation() {

    }


    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getIds();
            obList.addAll(idList);
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLocBackOnAction(ActionEvent event) {
    }

    @FXML
    void btnLocCleanOnAction(ActionEvent event) {
    }

    @FXML
    void btnLocDeleteOnAction(ActionEvent event) {
        String loId = txtLoId.getText();
        try {
            boolean isDeleted = LocationRepo.delete(loId);
            if (isDeleted) {
                Location selectedLocation = colLoTel.getSelectionModel().getSelectedItem();
                if (selectedLocation != null) {
                    colLoTel.getItems().remove(selectedLocation);
                    new Alert(Alert.AlertType.CONFIRMATION, "Location deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Location!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting Location: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnLocHomeOnAction(ActionEvent event) {
    }

    @FXML
    void btnLocNextOnAction(ActionEvent event) {
    }

    @FXML
    void btnLocSaveOnAction(ActionEvent event) {
        String cuId = cmbCustomerId.getValue();
        String loId = txtLoId.getText();
        String loProvince = txtLoProvince.getText();
        String loCity = txtLoCity.getText();
        String loAddress = txtLoAddress.getText();
        String loZipCode = txtLoZip.getText();

        Customer customer = new Customer(cuId);
        Location location = new Location(loId, loProvince, loCity, loAddress, loZipCode);

        try {
            boolean isSaved = LocationRepo.save(customer, location);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Location saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save location!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving location: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnLocUpdateOnAction(ActionEvent event) {
        String loId = txtLoId.getText();
        String loProvince = txtLoProvince.getText();
        String loCity = txtLoCity.getText();
        String loAddress = txtLoAddress.getText();
        String loZipCode = txtLoZip.getText();

        Location location = new Location(loId, loProvince, loCity, loAddress, loZipCode);

        try {
            boolean isUpdated = LocationRepo.update(location);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Location updated successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update location!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating location: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnNewCusOnAction(ActionEvent event) {
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        cmbCustomerId.getSelectionModel().clearSelection();
        txtLoId.clear();
        txtLoProvince.clear();
        txtLoCity.clear();
        txtLoAddress.clear();
        txtLoZip.clear();
    }
}
