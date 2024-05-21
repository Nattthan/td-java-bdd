import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SportsDAO {
    private MYSQLDatabase database;

    public SportsDAO(MYSQLDatabase database) {
        this.database = database;
    }

    public ArrayList<Sport> findAll() {
        try {
            database.connect();
            Statement myStatement = database.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM sport");
            ArrayList<Sport> sports = new ArrayList<Sport>(10);
            while (myResultSet.next()) {
                Sport sport = new Sport(myResultSet.getInt("id"), myResultSet.getString("name"),
                        myResultSet.getInt("required_participants"));
                sports.add(sport);
            }
            return sports;
        } catch (SQLException e) {
            System.out.println("Failed to find all sports.");
            return null;
        }
    }

    public Sport findById(int id) {
        try {
            database.connect();
            PreparedStatement myPreparedStatement = database.prepareStatement("SELECT * FROM sport WHERE id= ?");
            myPreparedStatement.setInt(1, id);
            ResultSet myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                Sport sport = new Sport(myResultSet.getInt("id"), myResultSet.getString("name"), myResultSet.getInt("required_participants"));
                return sport;
            } else {
                System.out.println("Can't find the id");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Can't find the id" + e.getMessage());
            return null;
        }
    }

    public ArrayList<Sport> findByName(String name) {
        try {
            database.connect();
            PreparedStatement myPreparedStatement = database.prepareStatement("SELECT * FROM sport WHERE name LIKE ?");
            myPreparedStatement.setString(1, "%" + name + "%");
            ResultSet myResultSet = myPreparedStatement.executeQuery();
            ArrayList<Sport> sports = new ArrayList<Sport>(10);
            while (myResultSet.next()) {
                Sport sport = new Sport(myResultSet.getInt("id"), myResultSet.getString("name"), myResultSet.getInt("required_participants"));
                sports.add(sport);
            }
            return sports;

        } catch (SQLException e) {
            System.out.println("Can't find the name" + e.getMessage());
            return null;
        }
    }

}
