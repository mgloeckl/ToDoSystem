import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.ToDo;

import java.io.IOException;

public class Controller {

    public ListView<ToDo> ToDoListView; //befüllen
    public ComboBox<Priority> priorityComboBox; //befüllen
    public ComboBox<Status> statusComboBox; // befüllen
    public TextField ToDoNameTextField;
    public Pane contentPane;

    ToDo selectedItem = null;


    public void onStatusClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("status.fxml"));

            Stage s = new Stage();
            s.setTitle("Status");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPriorityClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("priority.fxml"));

            Stage s = new Stage();
            s.setTitle("Prioritäten");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        ToDoListView.setItems(ToDo.getList());
        priorityComboBox.setItems(Priority.getList());
        statusComboBox.setItems(Status.getList());
    }


    public void onToDoListViewClicked(MouseEvent mouseEvent) {
        selectedItem = ToDoListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            //priorityComboBox.setValue();
            //statusComboBox.setValue();
        }
    }
}