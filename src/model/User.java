package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String street;
    private String city;
    private int postcode;
    private int principal_id;

    public User(String username, String street, String city, int postcode, int principal_id) {
        this.username = username;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.principal_id = principal_id;
    }

    public static ObservableList<User> getUser () {
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr6_Principal");
            ResultSet results = statement.executeQuery();

            while (results.next()){
                User tmp = new User(results.getString("username"), results.getString("street"), results.getString("city"), results.getInt("postcode"), results.getInt("principal_id"));
                list.add(tmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void updateUser(User u) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("UPDATE gr6_Principal SET username = '" + u.getUsername() + "'," + "street = '" + u.getStreet() + "'," + "postcode = '" + u.getPostcode() + "'," + "city = '" +u.getCity() + "'" + "WHERE principal_id = '" + String.valueOf(u.getPrincipal_id()) + "'");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addUser(User p) {

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        /*
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("INSERT INTO gr6_Principal(user_id, name) VALUES ('" + String.valueOf(p.getId()) + "', '" + p.getName() + "')");
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public int getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(int principal_id) {
        this.principal_id = principal_id;
    }

}
