package DatabaseHandler;

import model.Status;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseHandler {

    private static DatabaseHandler instance;

    public AbstractDatabase getConn() {
        return conn;
    }

    public void setConn(AbstractDatabase conn) {
        this.conn = conn;
    }

    private AbstractDatabase conn;
    private DatabaseHandler(){}

    public  static DatabaseHandler getInstance(){
        if(DatabaseHandler.instance == null){
            DatabaseHandler.instance = new DatabaseHandler();
        }
        return DatabaseHandler.instance;
    }

    public static AbstractDatabase generateConnector(){
        AbstractDatabase c = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        return c;
    }

    public  ResultSet read(String SQLString){
        ResultSet results = null;
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(SQLString);
            results = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public <T> void write(T object){
        //Todo
        /*INSERT INTO table_name (column1, column2, column3, ...)
            VALUES (value1, value2, value3, ...);*/
    }
    public <T> void delete(T SQLString){
        //Todo
        //DELETE FROM table_name WHERE condition;dsd
    }
}
