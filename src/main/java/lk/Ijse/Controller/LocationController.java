package lk.Ijse.Controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
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
        applyButtonAnimations();
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
                cmbCustomerId.setValue(newSelection.getCustomerId());
                txtLoId.setText(newSelection.getId());
                txtLoProvince.setText(newSelection.getProvince());
                txtLoCity.setText(newSelection.getCity());
                txtLoAddress.setText(newSelection.getAddress());
                txtLoZip.setText(newSelection.getZipCode());
            }
        });
    }
    private void applyButtonAnimations() {
        applyAnimation(btnNewCus);
        applyAnimation(btnLocBack);
        applyAnimation(btnLocClean);
        applyAnimation(btnLocDelete);
        applyAnimation(btnLocSave);
        applyAnimation(btnLocNext);
        applyAnimation(btnLocNext);
        applyAnimation(btnLocHome);


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


    private void setCellValueFactory() {
        colCu_ID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colZipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
    }

    private void loadAllLocation() {
        try {
            List<Location> locationList = LocationRepo.getAll();
            ObservableList<Location> obList = FXCollections.observableArrayList(locationList);
            colLoTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading locations: " + e.getMessage(), e);
        }
    }

    private void getCustomerIds() {
        try {
            List<String> idList = CustomerRepo.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
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
        clearFields();
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

        Location location = new Location(cuId, loId, loProvince, loCity, loAddress, loZipCode);

        try {
            boolean isSaved = LocationRepo.save(location);
            if (isSaved) {
                colLoTel.getItems().add(location);
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
        String cuId = cmbCustomerId.getValue();
        String loId = txtLoId.getText();
        String loProvince = txtLoProvince.getText();
        String loCity = txtLoCity.getText();
        String loAddress = txtLoAddress.getText();
        String loZipCode = txtLoZip.getText();

        Location location = new Location(cuId, loId, loProvince, loCity, loAddress, loZipCode);

        try {
            boolean isUpdated = LocationRepo.update(location);
            if (isUpdated) {
                int selectedIndex = colLoTel.getSelectionModel().getSelectedIndex();
                colLoTel.getItems().set(selectedIndex, location);
                new Alert(Alert.AlertType.CONFIRMATION, "Location updated successfully!").show();
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
