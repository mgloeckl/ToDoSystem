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
    int tmp = 0;

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
        } else if(nameTextField.getText().length() > 0 && streetTextField.getText().length() > 0 && cityTextField.getText().length() > 0 && plzTextField.getText().length() > 0){
            //insert new
            // String username, String street, String city, int postcode, int principal_id
            selectedItem = new User(nameTextField.getText(), streetTextField.getText(), cityTextField.getText(), Integer.parseInt(plzTextField.getText()), User.getUser().size() + 1);
            userListView.getItems().add(selectedItem);
            User.addUser(selectedItem);
        }else {
            if(nameTextField.getText().length() == 0){
                nameTextField.setStyle("-fx-border-color: red ;");
                System.out.println("Name is empty");
            }
            if(streetTextField.getText().length() == 0){
                streetTextField.setStyle("-fx-border-color: red ;");
                System.out.println("Street is empty");
            }
            if(plzTextField.getText().length() == 0){
                plzTextField.setStyle("-fx-border-color: red ;");
                System.out.println("PLZ is empty");
            }
            if(cityTextField.getText().length() == 0){
                cityTextField.setStyle("-fx-border-color: red ;");
                System.out.println("City is empty");
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

        nameTextField.setStyle("-fx-border-color: null ;");
        streetTextField.setStyle("-fx-border-color: null ;");
        plzTextField.setStyle("-fx-border-color: null ;");
        cityTextField.setStyle("-fx-border-color: null ;");
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            // delete item
            User.deleteUser(selectedItem);
            list.remove(selectedItem);

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
