package cat.proven.storeapp.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://" + "127.0.0.1/dbstore";
    private static final String USUARI = "storeusr";
    private static final String PASSWORD = "storepsw";

    public DBConnect() throws ClassNotFoundException {
        Class.forName(this.DRIVER);       
    }
    
    /**
     * 
     * @return a connection 
     * @throws SQLException if a connection error occurs
     */
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(BD_URL, USUARI, PASSWORD);
        
        return conn;
    }
}
