package Datahandler;

import javafx.collections.ObservableList;
import model.Priority;
import model.Status;

import java.sql.ResultSet;

public class Datahandler {
    public  static <T extends Status> void toList(ResultSet r, ObservableList o, Class<T> type){
        try {
            while (r.next()) {
                o.add(T.toOb(r));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
