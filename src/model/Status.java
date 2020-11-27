package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DatabaseHandler.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    private String status;
    private int statusId;

    public Status(String status, int statusId) {
        this.status = status;
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return status;
    }

    public static ObservableList<Status> getList(){
        ObservableList<Status> list = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        ResultSet res = databaseHandler.read("SELECT * FROM gr6_status");
        try {
            while (res.next()) {
                Status tmp = new Status(res.getString("name"), res.getInt("status_id"));
                list.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }
}
