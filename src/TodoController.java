import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.ToDo;

public class TodoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    private ToDo selected = null;

    ObservableList<ToDo> list = null;

    public void setToDo(ToDo item){
        selected = item;
        displayItem();
    }

    public void initialize(){
        priorityComboBox.setItems(Priority.getList());
        statusComboBox.setItems(Status.getList());
        list = ToDo.getTodo();
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
        Stage stage = (Stage)nameTextField.getScene().getWindow();
        stage.close();
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selected != null){
            selected.setName(nameTextField.getText());
            selected.setDescription(descriptionTextArea.getText());
            selected.setPriority_id(priorityComboBox.getSelectionModel().getSelectedItem().getId());
            selected.setStatus_id(statusComboBox.getSelectionModel().getSelectedItem().getId());
            ToDo.updateTodo(selected);
        } else if (nameTextField.getText().length() > 0){
            selected = new ToDo(list.size() + 1, nameTextField.getText(), descriptionTextArea.getText(), statusComboBox.getSelectionModel().getSelectedItem().getId(), priorityComboBox.getSelectionModel().getSelectedItem().getId());
            ToDo.addTodo(selected);

        } else {
            System.out.println("Input is empty!");
        }

    }

    public void newClicked(ActionEvent actionEvent) {
        clear();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if (selected != null){
            ToDo.deleteTodo(selected);
            list.remove(selected);
            clear();
        }else {
            System.out.println("Not Todo selected!");
        }
    }

    private void clear(){
        selected = null;
        nameTextField.clear();
        descriptionTextArea.clear();
        statusComboBox.getSelectionModel().clearSelection();
        priorityComboBox.getSelectionModel().clearSelection();
    }
}
