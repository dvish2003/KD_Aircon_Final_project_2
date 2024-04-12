package lk.Ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.Ijse.Db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private CheckBox btnTick;
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userID = txtUserID.getText();
        String password = txtPassword.getText();
        try {
            checkCredential(userID, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredential(String userID, String password) throws SQLException, IOException {

        String sql = "SELECT   Register_id, Register_Password FROM Register WHERE User_ID = ?  ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userID);

        ResultSet  resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbpw = resultSet.getString("Register_Password");
            if (password.equals(dbpw)) {
                navigateToDashBoard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid UserID").show();
        }
    }

    private void navigateToDashBoard() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/Register_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");

        stage.show();
    }
    @FXML
    void btnTickOnAction(ActionEvent event) {

    }
}
