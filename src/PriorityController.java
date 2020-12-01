import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;

public class PriorityController {

    public ListView<Priority> priorityListView;
    public TextField nameTextField;
    public Button cancelButton;

    Priority selectedItem = null;

    public void initialize(){
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        selectedItem = priorityListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            nameTextField.setText(selectedItem.getName());
        }

    }

    public void saveClicked(ActionEvent actionEvent) {

        if(selectedItem != null){
            //update existing item
            selectedItem.setName(nameTextField.getText());

        } else{
            //insert new
            Priority tmp = new Priority(Priority.getList().size() + 1, nameTextField.getText());
            priorityListView.getItems().add(tmp);
        }
        //initialize();
        priorityListView.refresh();
    }

    public void cancelClicked(ActionEvent actionEvent) {
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            // delete item
            priorityListView.getItems().remove(selectedItem.getId() - 1);
            selectedItem = null;
            nameTextField.clear();
            priorityListView.getSelectionModel().clearSelection();
        }

        priorityListView.refresh();
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        priorityListView.getSelectionModel().clearSelection();
    }
}
