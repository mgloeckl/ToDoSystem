package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.jshell.Snippet;
import model.db.AbstractDatabase;
import model.db.IDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Priority {

    private int id;
    private String name;

    public Priority(int priority_id, String name) {
        this.id = priority_id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> getList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr6_Priority");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Priority tmp = new Priority(resultSet.getInt("priority_id"), resultSet.getString("name"));

                list.add(tmp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }


    public static void updateList(Priority p) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("UPDATE gr6_Priority SET name = '" + p.getName() + "'" + "WHERE priority_id = '" + String.valueOf(p.getId()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addList(Priority p) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");


        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("INSERT INTO gr6_Priority(priority_id, name) VALUES ('" + String.valueOf(p.getId()) + "', '" + p.getName() + "')");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteList(Priority p) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");


        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("DELETE FROM gr6_Priority WHERE priority_id = '" + String.valueOf(p.getId()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}