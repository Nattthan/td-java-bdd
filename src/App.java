import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        PolySportsDatabase db = PolySportsDatabase.getInstance();
        try {
            db.connect();
            SportsDAO sportsDAO = new SportsDAO(db);
            ArrayList<Sport> sports = sportsDAO.findAll();
            for (Sport sport : sports) {
                System.out.println(sport.getId());
                System.out.println(sport.getName());
                System.out.println(sport.getRequiredParticipants());
            }

            System.out.println("Find by ID");

            Sport sport_id = sportsDAO.findById(1);
            System.out.println(sport_id.getId());
            System.out.println(sport_id.getName());
            System.out.println(sport_id.getRequiredParticipants());

            System.out.println("Find by NAME");

            Scanner myScanner = new Scanner(System.in);
            String input = myScanner.nextLine();
            myScanner.close();

            ArrayList<Sport> sports_named = sportsDAO.findByName(input);
            for (Sport sportz : sports_named) {
                System.out.println(sportz.getId());
                System.out.println(sportz.getName());
                System.out.println(sportz.getRequiredParticipants());
            }
        } catch (Exception e) {
            System.out.println("Database connection failed.");
        }

    }
}
