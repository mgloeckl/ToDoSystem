import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
import model.Status;

public class StatusController {
    public ListView<Status> statusListView;
    public TextField nameTextField;
    public Button cancelButton;


    ObservableList<Status> list = Status.getList();
    Status selectedItem = null;

    public void initialize() {
        statusListView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        selectedItem = statusListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            nameTextField.setText(selectedItem.getName());
        } else {
            // insert new
        }
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            //update existing item
            selectedItem.setName(nameTextField.getText());
            Status.updateList(selectedItem);
        } else{
            //insert new
            if (nameTextField.getText().length() > 0){
                selectedItem = new Status(nameTextField.getText(), list.size()+ 1);
                statusListView.getItems().add(selectedItem);
                Status.addList(selectedItem);
            } else {
                System.out.println("Input is empty!");
            }
        }

        statusListView.refresh();

    }

    public void cancelClicked(ActionEvent actionEvent) {
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            // delete item
            Status.deleteList(selectedItem);
            statusListView.getItems().remove(selectedItem.getId() - 1);


            selectedItem = null;
            nameTextField.clear();
            statusListView.getSelectionModel().clearSelection();
            statusListView.refresh();
        } else {
            System.out.println("No selected Item!");
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        statusListView.getSelectionModel().clearSelection();
    }
}
