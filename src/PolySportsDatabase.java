public class PolySportsDatabase extends MYSQLDatabase{
    static private PolySportsDatabase instance = null;

    private PolySportsDatabase(String host, int port, String databaseName, String user, String password){
        super(host, port, databaseName, user, password);
    }

    static public PolySportsDatabase getInstance(){
        if(PolySportsDatabase.instance == null){
            PolySportsDatabase.instance = new PolySportsDatabase("localhost", 3306, "poly_sports", "root", "");
        }
            return PolySportsDatabase.instance;
    }

}
