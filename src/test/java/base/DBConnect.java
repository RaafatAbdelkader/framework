package base;
import java.sql.*;


public class DBConnect {
    private String hostname=PropReader.getProp("hostname").toString();
    private String db_username=PropReader.getProp("db_username").toString();
    private String db_password=null;
    private String db_name=PropReader.getProp("db_name").toString();
    private String db_port=  PropReader.getProp("db_port").toString();
    private String connLink="jdbc:mysql://"+hostname+":"+db_port+"/"+db_name;
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(connLink, db_username,db_password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
      public ResultSet  executeQuery(String query){
        try {
             return getConnection().createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




}
