import java.sql.*;


public class Main
{

    public static void main(String[] args) throws SQLException
    {
        Database database = new Database();

        database.Select();
        database.Insert();
        database.Update();
        database.Delete();
    }

}
