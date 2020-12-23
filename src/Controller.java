import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.ToDo;

import java.io.IOException;
import java.util.function.Predicate;

public class Controller {

    public ListView<ToDo> ToDoListView; //befüllen
    public ComboBox<Priority> priorityComboBox; //befüllen
    public ComboBox<Status> statusComboBox; // befüllen
    public TextField ToDoNameTextField;
    public Pane contentPane;

    ToDo selectedItem = null;
    private ObservableList<ToDo> todoList;
    private ObservableList<Status> statusList = FXCollections.observableArrayList();
    private ObservableList<Priority> priorityList = FXCollections.observableArrayList();
    private Status selectedStatus = new Status("", -69);
    private Priority selectedPriority = new Priority(-69, "");
    private String selectedName = "";
    public void setToDoList(ObservableList<ToDo> list){
        this.todoList = list;
    }

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
        selectedStatus = new Status("", -69);
        selectedPriority = new Priority(-69, "");
        todoList = ToDo.getTodo();
        ToDoListView.setItems(todoList);
        priorityComboBox.setItems(Priority.getList());
        statusComboBox.setItems(Status.getList());
        loadComboBox();
    }


    public void onToDoListViewClicked(MouseEvent mouseEvent) {
        selectedItem = ToDoListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            /**
             * Stelle die Daten der gewählten ToDos auf der rechten Seite dar
             */
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("todo.fxml"));
                Pane todoPane = loader.load();

                TodoController controller = (TodoController) loader.getController();
                controller.setToDo(selectedItem);
                setToDoList(ToDoListView.getItems());

                contentPane.getChildren().add(todoPane);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void onUserClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("user.fxml"));

            Stage s = new Stage();
            s.setTitle("Benutzer");
            s.setScene(new Scene(root));
            s.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadComboBox() {
        statusList.add(new Status("All", -69));
        statusComboBox.getSelectionModel().select(new Status("All", -69));
        statusList = FXCollections.concat(statusList, Status.getList());
        statusComboBox.setItems(statusList);

        priorityList.add(new Priority(-69, "All"));
        priorityComboBox.getSelectionModel().select(new Priority(-69, "All"));
        priorityList = FXCollections.concat(priorityList, Priority.getList());
        priorityComboBox.setItems(priorityList);
    }

    private void filter(){
        /*todoList.filtered(new Predicate<ToDo>() {
            @Override
            public boolean test(ToDo toDo) {
                //if(selectedStatus.getId()==-69 && selectedPriority.getId() ==-69){
                  //  return true;
                //}
                if(toDo.getStatus_id()==selectedStatus.getId() && selectedPriority.getId() ==-69){
                    return true;
                }
                return true;
            }
        });
        ToDoListView.setItems(todoList);*/

        FilteredList<ToDo> filterdata = new FilteredList<>(todoList, predicate -> true);
        //filterdata.addListener((Change change));
        filterdata.setPredicate(ToDo -> {
        if(selectedName.equals("")) {
            if (selectedStatus.getId() == -69 && selectedPriority.getId() == -69) {
                return true;
            } else if (selectedStatus.getId() == ToDo.getStatus_id() && selectedPriority.getId() == -69) {
                return true;
            } else if (selectedStatus.getId() == -69 && selectedPriority.getId() == ToDo.getPriority_id()) {
                return true;
            } else if (selectedStatus.getId() == ToDo.getStatus_id() && selectedPriority.getId() == ToDo.getPriority_id()) {
                return true;//koks
            }
        }else if (!selectedName.equals("")){
            if (selectedStatus.getId() == -69 && selectedPriority.getId() == -69 && ToDo.getName().trim().replaceAll(" ", "").toLowerCase().contains(selectedName.toLowerCase().trim().replaceAll(" ", ""))) {
                return true;
            } else if (selectedStatus.getId() == ToDo.getStatus_id() && selectedPriority.getId() == -69 && ToDo.getName().trim().replaceAll(" ", "").toLowerCase().contains(selectedName.toLowerCase().trim().replaceAll(" ", ""))) {
                return true;
            } else if (selectedStatus.getId() == -69 && selectedPriority.getId() == ToDo.getPriority_id() && ToDo.getName().trim().replaceAll(" ", "").toLowerCase().contains(selectedName.toLowerCase().trim().replaceAll(" ", ""))) {
                return true;
            } else if (selectedStatus.getId() == ToDo.getStatus_id() && selectedPriority.getId() == ToDo.getPriority_id() && ToDo.getName().trim().replaceAll(" ", "").toLowerCase().contains(selectedName.toLowerCase().trim().replaceAll(" ", ""))) {
                return true;//koks
            }
        }

            return false;
        });
        ToDoListView.setItems(filterdata);
    }

    public void onStatusComboBoxClicked(ActionEvent actionEvent) {
        selectedStatus = statusComboBox.getValue();
        filter();
    }

    public void onPriorityComboBoxClicked(ActionEvent actionEvent) {
        selectedPriority = priorityComboBox.getValue();
        filter();
    }

    public void search_name(KeyEvent keyEvent) {
            selectedName = ToDoNameTextField.getText().toString();
            filter();
    }
}