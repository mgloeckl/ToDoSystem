import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Priority;

public class PriorityController {

    public ListView<Priority> priorityListView;
    public TextField nameTextField;


    public void initialize(){
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Priority p = priorityListView.getSelectionModel().getSelectedItem();
        if(p != null){
            nameTextField.setText(p.getName());
        }

    }

    public void saveClicked(ActionEvent actionEvent) {

        //if( != null){
            //update existing item
        //} else{
            //insert new
        //}
    }

    public void cancelClicked(ActionEvent actionEvent) {
        //close dialog
    }

    public void deleteClicked(ActionEvent actionEvent) {
        //if( != null){
            // delete item
        //}
    }

    public void newClicked(ActionEvent actionEvent) {
        //itemSelected = null;
        nameTextField.clear();
    }
}
