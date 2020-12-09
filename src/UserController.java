import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
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
    public ListView<User> userListView;

    ObservableList<User> list = User.getUser();
    User selectedItem = null;

    public void initialize() {
        userListView.setItems(list);
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)nameTextField.getScene().getWindow();
        stage.close();
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            //update existing item
            selectedItem.setUsername(nameTextField.getText());
            selectedItem.setStreet(streetTextField.getText());
            selectedItem.setCity(cityTextField.getText());
            selectedItem.setPostcode(Integer.parseInt(plzTextField.getText()));

            User.updateUser(selectedItem);
        } else{
            //insert new
            if(nameTextField.getText().length() > 0){
                // String username, String street, String city, int postcode, int principal_id
                selectedItem = new User(nameTextField.getText(), streetTextField.getText(), cityTextField.getText(), Integer.parseInt(plzTextField.getText()), list.size() + 1);
                userListView.getItems().add(selectedItem);
                User.addUser(selectedItem);
            }else {
                System.out.println("Input is empty!");
            }

        }

        userListView.refresh();
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        streetTextField.clear();
        cityTextField.clear();
        plzTextField.clear();
        userListView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            // delete item
            User.deleteUser(selectedItem);
            userListView.getItems().remove(selectedItem.getPrincipal_id() - 1);


            selectedItem = null;
            nameTextField.clear();
            streetTextField.clear();
            cityTextField.clear();
            plzTextField.clear();
            userListView.getSelectionModel().clearSelection();
            userListView.refresh();
        } else {
            System.out.println("No selected Item!");
        }
    }

    public void itemClicked(MouseEvent mouseEvent) {
        selectedItem = userListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            nameTextField.setText(selectedItem.getUsername());
            streetTextField.setText(selectedItem.getStreet());
            cityTextField.setText(selectedItem.getCity());
            plzTextField.setText(String.valueOf(selectedItem.getPostcode()));
        }
    }

}
