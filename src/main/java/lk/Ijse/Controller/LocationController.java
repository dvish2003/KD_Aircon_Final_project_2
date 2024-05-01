package lk.Ijse.Controller;

import javafx.animation.ScaleTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Model.Customer;
import lk.Ijse.Model.Location;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.LocationRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LocationController {


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
        txtLoZip.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLocSave.requestFocus();
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
        applyAnimation(btnLocClean);
        applyAnimation(btnLocDelete);
        applyAnimation(btnLocSave);
        applyAnimation(btnLocUpdate);
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
    void btnLocCleanOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnLocDeleteOnAction(ActionEvent event) {
        ObservableList<Location> selectedLocations = colLoTel.getSelectionModel().getSelectedItems();
        if (selectedLocations.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select location(s) to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected location(s)?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (Location location : selectedLocations) {
                    boolean isDeleted = LocationRepo.delete(location.getId());
                    if (isDeleted) {
                        colLoTel.getItems().remove(location);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete location: " + location.getId()).show();
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
    void btnNewCusOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerForm.fxml"));
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
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
