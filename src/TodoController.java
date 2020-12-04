import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Priority;
import model.Status;
import model.ToDo;

public class TodoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    private ToDo selected = null;

    public void setToDo(ToDo item){
        selected = item;
        displayItem();
    }

    public void initialize(){
        priorityComboBox.setItems(Priority.getList());
        statusComboBox.setItems(Status.getList());
    }

    private void displayItem(){
        /**
         * Hier sollen die Daten "item" angezeigt werden
         */
        //statusComboBox.setItems();

        nameTextField.setText(selected.getName());
        descriptionTextArea.setText(selected.getDescription());

        int i = 0;
        for (i = 0; i < statusComboBox.getItems().size(); i++){
            if(statusComboBox.getItems().get(i).getId() == selected.getStatus_id()){
                statusComboBox.getSelectionModel().select(i);
            }
            if(statusComboBox.getItems().get(i).getId() == selected.getPriority_id()){
                priorityComboBox.getSelectionModel().select(i);
            }
        }
    }

    public void cancelClicked(ActionEvent actionEvent) {
        
    }

    public void addClicked(ActionEvent actionEvent) {
        
    }
}
