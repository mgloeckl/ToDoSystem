import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StatusController {
    public TextField nameTextField;
    public ListView<Status> statusListView;

    public void initialize(){
        statusListView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();
        if(s != null){
            nameTextField.setText(s.toString());
        }
    }
}
