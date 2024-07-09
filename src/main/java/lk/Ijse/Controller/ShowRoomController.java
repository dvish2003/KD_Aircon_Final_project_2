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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.Animation1.Animation1;
import lk.Ijse.BO.BOFactory;
import lk.Ijse.BO.ShowroomBO.ShowRoomBO;
import lk.Ijse.BO.ShowroomBO.ShowRoomBOImpl;
import lk.Ijse.dto.ShowRoomDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowRoomController {
    public Label lblRegisterId;
    @FXML
    private  Button btnHome;
    @FXML
    private AnchorPane ShowRoomPane;

    @FXML
    private Button btnSrClean;

    @FXML
    private Button btnSrDelete;



    @FXML
    private Button btnSrSave;

    @FXML
    private Button btnSrUpdate;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colShowRoom;

    @FXML
    private TableView<ShowRoomDTO> colSrTel;



    @FXML
    private TextField txtSrLocation;



ShowRoomBO showRoomBO = (ShowRoomBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.ShowRoom);



    Animation1 animation1 = new Animation1();

    @FXML
    void btnPrCleanOnAction(ActionEvent event) throws ClassNotFoundException {
        clearFields();

    }

    private void clearFields() throws ClassNotFoundException {
        txtSrLocation.clear();
        getCurrentShowRoomID();
    }

    @FXML
    void btnPrDeleteOnAction(ActionEvent event) throws ClassNotFoundException {

        String id = lblRegisterId.getText();
        try {
            boolean isDeleted = showRoomBO.delete(id);
            if (isDeleted) {
                ShowRoomDTO showRoomDTO = (ShowRoomDTO) colSrTel.getSelectionModel().getSelectedItem();
                if (showRoomDTO != null) {
                    colSrTel.getItems().remove(showRoomDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "ShowRoom deleted successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete ShowRoom!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting ShowRoom: " + e.getMessage()).show();
        }
    }




    @FXML
    void btnPrSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblRegisterId.getText();
        String location = txtSrLocation.getText();


        ShowRoomDTO showRoomDTO = new ShowRoomDTO(id,location);

        try {
            boolean isSaved = showRoomBO.save(showRoomDTO);
            if (isSaved) {

                colSrTel.getItems().add(showRoomDTO);
                new Alert(Alert.AlertType.CONFIRMATION, "ShowRoom saved successfully!").show();
                clearFields();
                getCurrentShowRoomID();

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save ShowRoom!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving ShowRoom: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnPrUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = lblRegisterId.getText();
        String location = txtSrLocation.getText();


        ShowRoomDTO showRoomDTO = new ShowRoomDTO(id,location);
        try {
            boolean isUpdated = showRoomBO.update(showRoomDTO);
            if (isUpdated) {
                ShowRoomDTO selectedItem = colSrTel.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = colSrTel.getItems().indexOf(selectedItem);
                    colSrTel.getItems().set(selectedIndex, showRoomDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating customer: " + e.getMessage()).show();
        }

    }
    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllShowRoom();
        getCurrentShowRoomID();
        applyAnimation();


        txtSrLocation.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    btnSrSave.requestFocus();
                }
            });


        colSrTel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                lblRegisterId.setText(newSelection.getShowRoomId());
                txtSrLocation.setText(newSelection.getShowRoomLocation());

            }
        });
    }

    private void applyAnimation() {
        animation1.addHoverHandlers(btnSrClean);
        animation1.addHoverHandlers(btnSrUpdate);
        animation1.addHoverHandlers(btnSrDelete);
        animation1.addHoverHandlers(btnSrSave);
        animation1.addHoverHandlers(btnHome);
        animation1.applyAnimation(btnSrClean);
        animation1.applyAnimation(btnSrUpdate);
        animation1.applyAnimation(btnSrDelete);
        animation1.applyAnimation(btnSrSave);
        animation1.applyAnimation(btnHome);
    }


    private void setCellValueFactory() {
        colShowRoom.setCellValueFactory(new PropertyValueFactory<>("showRoomId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("showRoomLocation"));
    }

    private void loadAllShowRoom() throws ClassNotFoundException {
        ObservableList<ShowRoomDTO> obList = FXCollections.observableArrayList();
        try {
            List<ShowRoomDTO> showRoomDTOList = showRoomBO.getAll();
            for (ShowRoomDTO showRoomDTO : showRoomDTOList) {
                ShowRoomDTO tm = new ShowRoomDTO(
                        showRoomDTO.getShowRoomId(),
                        showRoomDTO.getShowRoomLocation()

                );
                obList.add(tm);
            }
            colSrTel.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnHomeOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }



    private void getCurrentShowRoomID() throws ClassNotFoundException {
        try {
            String currentId = showRoomBO.getCurrentId();

            String nextShowRoomID = generateNextShowRoom(currentId);
            lblRegisterId.setText(nextShowRoomID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextShowRoom(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return "S" + String.format("%03d", idNum);
        }
        return "S001";

}


public void LocationK(KeyEvent keyEvent) {
      //  CustomerRegex.setTextColor(CustomerTextField.,txtShowRoomID);

    }
}
