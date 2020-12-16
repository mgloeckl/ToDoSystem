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


    ObservableList<Priority> list = Priority.getList();
    Priority selectedItem = null;

    public void initialize(){
        priorityListView.setItems(list);

        //list.add(new Priority(-1, "Filter auswählen"));
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
            Priority.updateList(selectedItem);
        } else{
            //insert new
            if(nameTextField.getText().length() > 0){
                selectedItem = new Priority(list.size() + 1, nameTextField.getText());
                priorityListView.getItems().add(selectedItem);
                Priority.addList(selectedItem);
            }else {
                System.out.println("Input is empty!");
            }
        }

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
            Priority.deleteList(selectedItem);
            list.remove(selectedItem);


            selectedItem = null;
            nameTextField.clear();
            priorityListView.getSelectionModel().clearSelection();
            priorityListView.refresh();
        } else {
            System.out.println("No selected Item!");
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        priorityListView.getSelectionModel().clearSelection();
    }
}
