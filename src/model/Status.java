package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    private String name;
    private int id;

    public Status(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return name;
    }
    public static ObservableList<Status> getList(){
        ObservableList<Status> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr6_status");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                Status tmp = new Status(results.getString("name"), results.getInt("status_id"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void updateList(Status s) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("UPDATE gr6_status SET name = '" + s.getName() + "'" + "WHERE status_id = '" + String.valueOf(s.getId()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addList(Status s) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");


        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("INSERT INTO gr6_status(status_id, name) VALUES (" + "NULL, '" + s.getName() + "')");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteList(Status s) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");


        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("DELETE FROM gr6_status WHERE status_id = '" + String.valueOf(s.getId()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}