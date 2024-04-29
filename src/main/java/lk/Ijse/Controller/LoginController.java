package lk.Ijse.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.Ijse.Db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyButtonAnimations();
        txtUserId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPassword.requestFocus();
            }
        });

        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin.fire();
            }
        });
    }
    private void applyButtonAnimations() {
        applyAnimation(btnLogin);


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


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userID = txtUserId.getText();
        String password = txtPassword.getText();
        try {
            checkCredential(userID, password, event);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredential(String userId, String password, ActionEvent event) throws SQLException, IOException {
        String sql = "SELECT   Register_id, Register_Password FROM Register WHERE Register_id = ?  ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbpw = resultSet.getString("Register_Password");
            if (password.equals(dbpw)) {
                navigateToDashBoard(event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid UserID").show();
        }
    }

    private void navigateToDashBoard(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_from.fxml"));
        Parent rootNode = loader.load();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(950), rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }
}
