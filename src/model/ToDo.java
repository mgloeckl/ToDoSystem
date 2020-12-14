package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {
    private int id;
    private String name;
    private String description;
    private int status_id;
    private int priority_id;

    public ToDo(int todo_id, String name, String description, int status_id, int priority_id) {
        this.id = todo_id;
        this.name = name;
        this.description = description;
        this.status_id = status_id;
        this.priority_id = priority_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(int priority_id) {
        this.priority_id = priority_id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<ToDo> getTodo() {
        ObservableList<ToDo> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr6_todo");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ToDo tmp = new ToDo(resultSet.getInt("todo_id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("status_id"), resultSet.getInt("priority_id"));

                list.add(tmp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public static void updateTodo(ToDo t) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("UPDATE gr6_todo SET name = '" + t.getName() + "', " + "description = '" + t.getDescription() + "', " + "status_id = '" + t.getStatus_id() + "', " + "priority_id = '" + t.getPriority_id() + "'" + "WHERE todo_id = '" + String.valueOf(t.getId()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addTodo(ToDo t){
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("INSERT INTO gr6_todo(todo_id, name, description, status_id, priority_id) VALUES (" + "NULL, '" + t.getDescription() + "', '" + t.getStatus_id() + "', '" + t.getPriority_id() + "')");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteTodo(ToDo t){
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");


        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("DELETE FROM gr6_todo WHERE todo_id = " + t.getId());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
