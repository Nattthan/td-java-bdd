import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MYSQLDatabase {
    private String host;
    private int port;
    private String databaseName;
    private String user;
    private String password;
    private Connection connection;
    static private boolean driverLoaded;

    public MYSQLDatabase(String host, int port, String databaseName, String user, String password) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.connection = null;
        MYSQLDatabase.driverLoaded = false;

    }

    public void connect() {
        try {
            loadDriver();
            if (driverLoaded) {
                String connectionUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.databaseName + "?allowMultiQueries=true";
                Connection myConnection = DriverManager.getConnection(connectionUrl, user, password);
                this.connection = myConnection;
            } else {
                System.out.println("Driver not loaded");
                loadDriver();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public Statement createStatement() {
        try {
            if(this.connection != null){    
                return this.connection.createStatement();
            }
            else{
                System.out.println("Connection not established");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            MYSQLDatabase.driverLoaded = true;
        } catch (Exception e) {
            System.err.println("Can't load the driver");
        }
    }

    public PreparedStatement prepareStatement(String sql){
        try {
            if (this.connection != null) {
                return this.connection.prepareStatement(sql);
            } else {
                System.out.println("Connection is null");
                return null;
            }
        } catch (SQLException e) {
           System.out.println("Error preparing statement: " + e.getMessage());
           return null;
        }
    }
}


