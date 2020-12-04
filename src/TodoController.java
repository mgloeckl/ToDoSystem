import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Status;
import model.ToDo;

public class TodoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox<Status> statusComboBox;
    public ComboBox priorityComboBox;

    private ToDo selected = null;

    public void setToDo(ToDo item){
        selected = item;
        displayItem();
    }

    private void displayItem(){
        /**
         * Hier sollen die Daten "item" angezeigt werden
         */
        //statusComboBox.setItems();

        nameTextField.setText(selected.getName());

        int i = 0;
        for (i = 0; i < statusComboBox.getItems().size(); i++){
            if(statusComboBox.getItems().get(i).getId() == selected.getStatus_id()){
                break;
            }
        }

        statusComboBox.getSelectionModel().select(i);
    }

    public void cancelClicked(ActionEvent actionEvent) {
        
    }

    public void addClicked(ActionEvent actionEvent) {
        
    }
}
