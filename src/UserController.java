import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Status;
import model.User;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController {

    public TextField nameTextField;
    public TextField cityTextField;
    public TextField plzTextField;
    public TextField streetTextField;

    private User user;

    public void initialize() {
        user = User.getUser(1);

        nameTextField.setText(user.getUsername());
        streetTextField.setText(user.getStreet());
        cityTextField.setText(user.getCity());
        plzTextField.setText(String.valueOf(user.getPostcode()));
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)nameTextField.getScene().getWindow();
        stage.close();
    }

    public void saveClicked(ActionEvent actionEvent) {
        user.setUsername(nameTextField.getText());
        user.setStreet(streetTextField.getText());
        user.setCity(cityTextField.getText());
        user.setPostcode(Integer.valueOf(plzTextField.getText()));

        User.updateUser(user);
    }
}
